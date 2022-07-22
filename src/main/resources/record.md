### 思考


云原生，虚拟化，私有云
SpringCloud\Mybatis原理
JOOQ、flyway、consul
效率 生活 (笔记) 技术 分布式、消息队列、nacos、zk、k8

日记 点日历写日记 type: 0817  tag:0-5，默认记录设置默认值，默认策略(工作日、周)，自定义策略,自动采集记录(H)
每天23:55 查询未写且开启策略的
config
type_code = 0001
json = {"strategyOpen":1}
create_by = wqy

-- 查找个人配置 开启自动补充日记策略的人
select create_by from config where json->'$.strategyOpen' = 1 and type_code = 0001;

-- 查找日记配置
select create_by,json from config where create_by in(wqy) and type_code = 0817 

Map -> (create_by,json)



策略 json {"1":{"tag":"3","content":"哈哈"},"2":{}...}


雷达图

- user
- note


db
- momento

整体：注册/登录、时刻、积分、时刻记录、个人日记、笔记、收藏网上文章(链接转文字存储)、大事记