-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
                         `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
                         `name` varchar(60) NOT NULL DEFAULT '' COMMENT '标签名称',
                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                         `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除标志位：0：未删除 1：已删除',
                         PRIMARY KEY (`id`) USING BTREE,
                         UNIQUE KEY `uk_name` (`name`) USING BTREE,
                         KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文章标签表';

-- ----------------------------
-- Records of t_tag
-- ----------------------------
BEGIN;
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (1, 'java', '2024-12-31 16:38:33', '2024-12-31 16:38:33', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (2, 'python', '2024-12-31 16:38:33', '2024-12-31 16:38:33', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (3, 'php', '2024-12-31 16:38:33', '2024-12-31 16:38:33', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (9, 'css', '2025-01-02 16:08:42', '2025-01-02 16:08:42', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (10, 'html', '2025-01-02 16:09:15', '2025-01-02 16:09:15', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (11, 'js', '2025-01-02 16:09:15', '2025-01-02 16:09:15', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (12, '新的标签1', '2025-01-10 17:05:22', '2025-01-10 17:05:22', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (13, '新的标签2', '2025-01-10 17:05:22', '2025-01-10 17:05:22', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (14, '新的标签3', '2025-01-10 17:08:05', '2025-01-10 17:08:05', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (15, '新的标签4', '2025-01-10 17:08:05', '2025-01-10 17:08:05', 0);
INSERT INTO `t_tag` (`id`, `name`, `create_time`, `update_time`, `is_deleted`) VALUES (16, 'test', '2025-01-10 17:34:27', '2025-01-10 17:34:27', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                          `username` varchar(60) NOT NULL COMMENT '用户名',
                          `password` varchar(60) NOT NULL COMMENT '密码',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                          `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除：0：未删除 1：已删除',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (1, 'admin@mail.com', '$2a$10$2SVI0SVzhQN7ZLBRGTT3.eLwtAg2WU1llnu5lxbMdK3XQukgd9NDO', '2024-12-24 01:23:48', '2024-12-27 17:33:05', 0);
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (2, 'test@mail.com', '$2a$10$mZ7pPSI.HcM43x6oUaiLBuCt9nfdnMtiYwvVpW3MhqvJnfjvGit3e', '2024-12-24 14:38:37', '2024-12-24 14:38:41', 0);
INSERT INTO `t_user` (`id`, `username`, `password`, `create_time`, `update_time`, `is_deleted`) VALUES (3, '犬小哈1', '123456', '2025-01-07 17:20:26', '2025-01-07 17:20:26', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `username` varchar(60) NOT NULL COMMENT '用户名',
                               `role` varchar(60) NOT NULL COMMENT '角色',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               KEY `idx_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (1, 'admin@mail.com', 'ROLE_ADMIN', '2023-07-07 01:21:15');
INSERT INTO `t_user_role` (`id`, `username`, `role`, `create_time`) VALUES (2, 'test@mail.com', 'ROLE_VISITOR', '2023-07-07 01:23:33');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
    -- Table structure for projects
-- ---
DROP TABLE IF EXISTS `projects`;

CREATE TABLE projects (
                          project_id VARCHAR(36) NOT NULL DEFAULT (UUID()) COMMENT '项目ID',
                          project_name VARCHAR(255) NOT NULL COMMENT '项目名称',
                          owner_id VARCHAR(36) COMMENT '负责人ID',
                          status ENUM('planning', 'in_progress', 'paused', 'completed', 'archived')
       NOT NULL DEFAULT 'planning' COMMENT '状态',
                          priority TINYINT UNSIGNED NOT NULL DEFAULT 3
       COMMENT '优先级(1-紧急 2-高 3-中 4-低)',
                          description TEXT COMMENT '项目描述',
                          start_time TIMESTAMP NULL COMMENT '计划开始时间',
                          end_time TIMESTAMP NULL COMMENT '计划截止时间',
                          completion_time TIMESTAMP NULL COMMENT '实际完成时间',
                          version INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁版本号',
                          is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '软删除标记',
                          create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          update_time TIMESTAMP NOT NULL
                                                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                          PRIMARY KEY (project_id),
                          INDEX idx_status_priority (status, priority),
                          INDEX idx_owner (owner_id),
                          INDEX idx_time_range (start_time, end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目主表';

-- ---
-- Table structure for tasks
-- ---
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE tasks (
                       task_id VARCHAR(36) NOT NULL DEFAULT (UUID()) COMMENT '任务ID',
                       task_name VARCHAR(255) NOT NULL COMMENT '任务名称',
                       project_id VARCHAR(36) COMMENT '关联项目ID',
                       owner_id VARCHAR(36) COMMENT '负责人ID',
                       status ENUM('backlog', 'todo', 'in_progress', 'review', 'completed')
       NOT NULL DEFAULT 'backlog' COMMENT '状态',
                       priority TINYINT UNSIGNED NOT NULL DEFAULT 3
       COMMENT '优先级(1-紧急 2-高 3-中 4-低)',
                       tag_ids JSON COMMENT '标签ID集合',
                       pre_task_id VARCHAR(36) COMMENT '前置任务ID',
                       estimate_hours DECIMAL(5,1) UNSIGNED COMMENT '预计工时(小时)',
                       start_time TIMESTAMP NULL COMMENT '计划开始时间',
                       end_time TIMESTAMP NULL COMMENT '计划截止时间',
                       completion_time TIMESTAMP NULL COMMENT '实际完成时间',
                       version INT UNSIGNED NOT NULL DEFAULT 1 COMMENT '乐观锁版本号',
                       is_deleted TINYINT(1) NOT NULL DEFAULT 0 COMMENT '软删除标记',
                       create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       update_time TIMESTAMP NOT NULL
                           DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                       PRIMARY KEY (task_id),
                       FOREIGN KEY (project_id)
                           REFERENCES projects(project_id)
                           ON UPDATE CASCADE ON DELETE SET NULL,
                       INDEX idx_project_due (project_id, end_time),
                       INDEX idx_status_due (status, end_time),
                       INDEX idx_owner_priority (owner_id, priority),
                       INDEX idx_pre_task (pre_task_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='原子任务表';