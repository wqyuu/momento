package com.wqy.momento.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.wqy.momento.config.MomentResponse;
import com.wqy.momento.config.ResultCode;
import com.wqy.momento.entity.Timing;
import com.wqy.momento.entity.UserPoint;
import com.wqy.momento.entity.vo.TimingRecordResp;
import com.wqy.momento.entity.vo.TimingRecordVO;
import com.wqy.momento.mapper.TimingMapper;
import com.wqy.momento.mapper.UserPointMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.wqy.momento.entity.TimingRecord;
import com.wqy.momento.mapper.TimingRecordMapper;
import com.wqy.momento.service.TimingRecordService;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 时间点记录;(timing_record)表服务实现类
 *
 * @author : http://www.chiner.pro
 * @date : 2022-7-14
 */
@Slf4j
@Service
public class TimingRecordServiceImpl implements TimingRecordService {
    @Autowired
    private TimingRecordMapper timingRecordMapper;
    @Autowired
    private TimingMapper timingMapper;
    @Autowired
    private UserPointMapper userPointMapper;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public TimingRecord queryById(Integer id) {
        return timingRecordMapper.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param timingRecord 筛选条件
     * @param current      当前页码
     * @param size         每页大小
     * @return
     */
    public Page<TimingRecord> paginQuery(TimingRecord timingRecord, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<TimingRecord> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(timingRecord.getTenantId())) {
            queryWrapper.eq(TimingRecord::getTenantId, timingRecord.getTenantId());
        }
        if (StrUtil.isNotBlank(timingRecord.getRevision())) {
            queryWrapper.eq(TimingRecord::getRevision, timingRecord.getRevision());
        }
        if (StrUtil.isNotBlank(timingRecord.getCreatedBy())) {
            queryWrapper.eq(TimingRecord::getCreatedBy, timingRecord.getCreatedBy());
        }
        if (StrUtil.isNotBlank(timingRecord.getUpdatedBy())) {
            queryWrapper.eq(TimingRecord::getUpdatedBy, timingRecord.getUpdatedBy());
        }
        if (StrUtil.isNotBlank(timingRecord.getTimingCode())) {
            queryWrapper.eq(TimingRecord::getTimingCode, timingRecord.getTimingCode());
        }
        if (StrUtil.isNotBlank(timingRecord.getDegree())) {
            queryWrapper.eq(TimingRecord::getDegree, timingRecord.getDegree());
        }
        //2. 执行分页查询
        Page<TimingRecord> pagin = new Page<>(current, size, true);
        pagin.addOrder(OrderItem.desc("FINISH_TIME"));
        IPage<TimingRecord> selectResult = timingRecordMapper.selectByPage(pagin, queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param timingRecord 实例对象
     * @return 实例对象
     */
    public TimingRecord insert(TimingRecord timingRecord) {
        timingRecordMapper.insert(timingRecord);
        return timingRecord;
    }

    /**
     * 更新数据
     *
     * @param timingRecord 实例对象
     * @return 实例对象
     */
    public TimingRecord update(TimingRecord timingRecord) {
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<TimingRecord> chainWrapper = new LambdaUpdateChainWrapper<TimingRecord>(timingRecordMapper);
        if (StrUtil.isNotBlank(timingRecord.getTenantId())) {
            chainWrapper.eq(TimingRecord::getTenantId, timingRecord.getTenantId());
        }
        if (StrUtil.isNotBlank(timingRecord.getRevision())) {
            chainWrapper.eq(TimingRecord::getRevision, timingRecord.getRevision());
        }
        if (StrUtil.isNotBlank(timingRecord.getCreatedBy())) {
            chainWrapper.eq(TimingRecord::getCreatedBy, timingRecord.getCreatedBy());
        }
        if (StrUtil.isNotBlank(timingRecord.getUpdatedBy())) {
            chainWrapper.eq(TimingRecord::getUpdatedBy, timingRecord.getUpdatedBy());
        }
        if (StrUtil.isNotBlank(timingRecord.getTimingCode())) {
            chainWrapper.eq(TimingRecord::getTimingCode, timingRecord.getTimingCode());
        }
        if (StrUtil.isNotBlank(timingRecord.getDegree())) {
            chainWrapper.eq(TimingRecord::getDegree, timingRecord.getDegree());
        }
        //2. 设置主键，并更新
        chainWrapper.set(TimingRecord::getId, timingRecord.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if (ret) {
            return queryById(timingRecord.getId());
        } else {
            return timingRecord;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        int total = timingRecordMapper.deleteById(id);
        return total > 0;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public MomentResponse add(TimingRecordVO timingRecordVO) {
        TimingRecord timingRecord = new TimingRecord();
        BeanUtils.copyProperties(timingRecordVO,timingRecord);
        // 验证timing是否存在
        if(this.checkTiming(timingRecordVO)){
            log.error("timing is no exist");
            return MomentResponse.failure(ResultCode.RESULE_DATA_NONE);
        }

        // 查询timing信息
        LambdaQueryWrapper<Timing> timQuery = new LambdaQueryWrapper<>();
        timQuery.eq(Timing::getCode, timingRecordVO.getTimingCode());
        Timing timing = timingMapper.selectOne(timQuery);
        timingRecord.setPoint(timing.getPoint());
        timingRecord.setType(timing.getType());

        // 积分变动
        BigDecimal point = this.pointChange(timingRecord);
        if(null == point){
           return MomentResponse.failure(ResultCode.DATA_IS_WRONG);
        }
        timingRecord.setPointChange(point);
        timingRecordMapper.insert(timingRecord);
        return MomentResponse.ok("success");
    }

    @Override
    public Page<TimingRecordResp> paginQueryByUser(TimingRecord timingRecord, long current, long size) {
        //1. 构建动态查询条件
        LambdaQueryWrapper<TimingRecord> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(timingRecord.getTenantId())) {
            queryWrapper.eq(TimingRecord::getTenantId, timingRecord.getTenantId());
        }
        if (StrUtil.isNotBlank(timingRecord.getRevision())) {
            queryWrapper.eq(TimingRecord::getRevision, timingRecord.getRevision());
        }
        if (StrUtil.isNotBlank(timingRecord.getCreatedBy())) {
            queryWrapper.eq(TimingRecord::getCreatedBy, timingRecord.getCreatedBy());
        }
        if (StrUtil.isNotBlank(timingRecord.getUpdatedBy())) {
            queryWrapper.eq(TimingRecord::getUpdatedBy, timingRecord.getUpdatedBy());
        }
        if (StrUtil.isNotBlank(timingRecord.getTimingCode())) {
            queryWrapper.eq(TimingRecord::getTimingCode, timingRecord.getTimingCode());
        }
        if (StrUtil.isNotBlank(timingRecord.getDegree())) {
            queryWrapper.eq(TimingRecord::getDegree, timingRecord.getDegree());
        }
        //2. 执行分页查询
        Page<TimingRecord> pagin = new Page<>(current, size, true);
        Page<TimingRecordResp> rsPagin = new Page<>(current, size, true);
        pagin.addOrder(OrderItem.desc("FINISH_TIME"));
        IPage<TimingRecordResp> selectResult = timingRecordMapper.selectUser(pagin, queryWrapper);
        rsPagin.setPages(selectResult.getPages());
        rsPagin.setTotal(selectResult.getTotal());
        rsPagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return rsPagin;
    }

    private boolean checkTiming(TimingRecordVO timingRecord){
        Map<String,Object> query = new HashMap<>();
        query.put("code",timingRecord.getTimingCode());
        query.put("created_by",timingRecord.getCreatedBy());
        List<Timing> timings = timingMapper.selectByMap(query);
        return CollectionUtils.isEmpty(timings);
    }

    /**
     * 积分变动
     * @param timingRecord
     */
    private BigDecimal pointChange(TimingRecord timingRecord){
        // 查询剩余积分
        LambdaQueryWrapper<UserPoint> userPointQuery = new LambdaQueryWrapper<>();
        userPointQuery.eq(UserPoint::getUserId, timingRecord.getCreatedBy());
        UserPoint pointData = userPointMapper.selectOne(userPointQuery);
        boolean isPresent = Optional.ofNullable(pointData).isPresent();

        // 判断是否为奖励类型
        if ("1".equals(timingRecord.getType())) {
            return this.minusPoint(isPresent,pointData,timingRecord);
        }else {
            return this.plusPoint(isPresent,pointData,timingRecord);
        }
    }

    /**
     * 扣减积分
     * @param isPresent 是否有积分
     * @param pointData 原有积分
     * @param timingRecord 新增记录
     */
    private BigDecimal minusPoint(boolean isPresent, UserPoint pointData,TimingRecord timingRecord){
        if(!isPresent){
            // 无积分，退出扣减
            log.error("timingRecord add error, no point");
            return null;
        }
        // 是否积分超出
        BigDecimal remainingPoint = pointData.getPoint();
        BigDecimal needPoint = timingRecord.getPoint();
        if(remainingPoint.compareTo(needPoint.abs())<0){
            log.error("timingRecord add error, point insufficient");
            return null;
        }
        // 减去使用积分
        BigDecimal point = remainingPoint.add(needPoint);
        pointData.setPoint(point);
        userPointMapper.updateById(pointData);
        return point;
    }

    private BigDecimal plusPoint(boolean isPresent, UserPoint pointData,TimingRecord timingRecord){
        if(!isPresent){
            // 新增积分
            UserPoint userPoint = new UserPoint();
            userPoint.setPoint(timingRecord.getPoint());
            userPoint.setUserId(timingRecord.getCreatedBy());
            userPointMapper.insert(userPoint);
            return timingRecord.getPoint();
        }else{
            // 更新积分
            BigDecimal remainingPoint = pointData.getPoint();
            BigDecimal updatePoint = remainingPoint.add(timingRecord.getPoint());
            pointData.setPoint(updatePoint);
            userPointMapper.updateById(pointData);
            return updatePoint;
        }
    }
}