/*
 Navicat Premium Dump SQL

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : localhost:3306
 Source Schema         : pms

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 27/02/2025 18:12:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `project_id` varchar(36) NOT NULL DEFAULT (uuid()) COMMENT '项目ID',
  `project_name` varchar(255) NOT NULL COMMENT '项目名称',
  `owner_id` varchar(36) DEFAULT NULL COMMENT '负责人ID',
  `status` enum('planning','in_progress','paused','completed','deprecated') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'planning' COMMENT '状态',
  `priority` tinyint unsigned NOT NULL DEFAULT '3' COMMENT '优先级(1-紧急 2-高 3-中 4-低)',
  `description` text COMMENT '项目描述',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '计划开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '计划截止时间',
  `completion_time` timestamp NULL DEFAULT NULL COMMENT '实际完成时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁版本号',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除标记',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`project_id`),
  KEY `idx_status_priority` (`status`,`priority`),
  KEY `idx_owner` (`owner_id`),
  KEY `idx_time_range` (`start_time`,`end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='项目主表';

-- ----------------------------
-- Records of projects
-- ----------------------------
BEGIN;
INSERT INTO `projects` (`project_id`, `project_name`, `owner_id`, `status`, `priority`, `description`, `start_time`, `end_time`, `completion_time`, `version`, `is_deleted`, `create_time`, `update_time`) VALUES ('955f1c2097685c11be1feb231532e1c6', 'PMS', NULL, 'in_progress', 2, 'PMS 开发', '2025-02-26 15:55:03', '2025-03-26 15:55:03', NULL, 0, 0, '2025-02-26 15:55:10', '2025-02-26 15:55:17');
INSERT INTO `projects` (`project_id`, `project_name`, `owner_id`, `status`, `priority`, `description`, `start_time`, `end_time`, `completion_time`, `version`, `is_deleted`, `create_time`, `update_time`) VALUES ('cc31e52f232d1f7575b2b77eced72e1f', 'PMS', NULL, 'deprecated', 2, 'PMS 开发', '2025-02-25 12:00:00', '2025-02-28 12:00:00', NULL, 0, 0, '2025-02-25 16:50:02', '2025-02-25 17:00:46');
INSERT INTO `projects` (`project_id`, `project_name`, `owner_id`, `status`, `priority`, `description`, `start_time`, `end_time`, `completion_time`, `version`, `is_deleted`, `create_time`, `update_time`) VALUES ('e289b511ec5cedcc2d0da3a77f09ade7', 'PMS项目', '1', 'completed', 2, 'PMS 开发', '2025-02-04 12:00:00', '2025-02-08 12:00:00', '2025-02-25 16:34:52', 0, 1, '2025-02-24 09:23:49', '2025-02-25 08:35:14');
COMMIT;

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

-- ----------------------------
-- Table structure for task_tag
-- ----------------------------
DROP TABLE IF EXISTS `task_tag`;
CREATE TABLE `task_tag` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `task_id` varchar(36) NOT NULL COMMENT '任务ID',
  `tag_id` bigint unsigned NOT NULL COMMENT '标签ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '软删除标记：0-未删除，1-已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_task_tag` (`task_id`,`tag_id`) COMMENT '任务和标签的唯一约束',
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `task_tag_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务-标签关系表';

-- ----------------------------
-- Records of task_tag
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tasks
-- ----------------------------
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `task_id` varchar(36) NOT NULL DEFAULT (uuid()) COMMENT '任务ID',
  `task_name` varchar(255) NOT NULL COMMENT '任务名称',
  `project_id` varchar(36) DEFAULT NULL COMMENT '关联项目ID',
  `owner_id` varchar(36) DEFAULT NULL COMMENT '负责人ID',
  `status` enum('todo','in_progress','completed','deprecated','wait') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'todo' COMMENT '状态',
  `priority` tinyint unsigned NOT NULL DEFAULT '3' COMMENT '优先级(1-紧急 2-高 3-中 4-低)',
  `tag_ids` json DEFAULT NULL COMMENT '标签ID集合',
  `pre_task_id` varchar(36) DEFAULT NULL COMMENT '前置任务ID',
  `estimate_hours` decimal(5,1) unsigned DEFAULT NULL COMMENT '预计工时(小时)',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '计划开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '计划截止时间',
  `completion_time` timestamp NULL DEFAULT NULL COMMENT '实际完成时间',
  `version` int unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁版本号',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '软删除标记',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`),
  KEY `idx_project_due` (`project_id`,`end_time`),
  KEY `idx_status_due` (`status`,`end_time`),
  KEY `idx_owner_priority` (`owner_id`,`priority`),
  KEY `idx_pre_task` (`pre_task_id`),
  CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='原子任务表';

-- ----------------------------
-- Records of tasks
-- ----------------------------
BEGIN;
INSERT INTO `tasks` (`task_id`, `task_name`, `project_id`, `owner_id`, `status`, `priority`, `tag_ids`, `pre_task_id`, `estimate_hours`, `start_time`, `end_time`, `completion_time`, `version`, `is_deleted`, `create_time`, `update_time`) VALUES ('23318b61219dd8b7825267212d461225', '任务开发', NULL, NULL, 'deprecated', 2, NULL, NULL, 1.0, '2025-02-05 12:00:00', '2025-02-06 12:00:00', NULL, 0, 0, '2025-02-26 15:31:44', '2025-02-26 15:47:19');
INSERT INTO `tasks` (`task_id`, `task_name`, `project_id`, `owner_id`, `status`, `priority`, `tag_ids`, `pre_task_id`, `estimate_hours`, `start_time`, `end_time`, `completion_time`, `version`, `is_deleted`, `create_time`, `update_time`) VALUES ('54bafc307d3b75817e50e99e4080aba6', '任务开发', NULL, NULL, 'todo', 4, NULL, NULL, 1.0, '2025-02-26 15:54:36', '2025-03-05 15:54:36', NULL, 0, 1, '2025-02-26 15:54:09', '2025-02-26 07:55:27');
INSERT INTO `tasks` (`task_id`, `task_name`, `project_id`, `owner_id`, `status`, `priority`, `tag_ids`, `pre_task_id`, `estimate_hours`, `start_time`, `end_time`, `completion_time`, `version`, `is_deleted`, `create_time`, `update_time`) VALUES ('8c9e8df7563c1b751f1c18ba4417886d', '任务管理开发', 'e289b511ec5cedcc2d0da3a77f09ade7', '1', 'completed', 2, NULL, NULL, 2.0, '2025-02-05 12:00:00', '2025-02-08 12:00:00', '2025-02-26 11:17:05', 0, 1, '2025-02-24 09:26:05', '2025-02-26 07:08:45');
INSERT INTO `tasks` (`task_id`, `task_name`, `project_id`, `owner_id`, `status`, `priority`, `tag_ids`, `pre_task_id`, `estimate_hours`, `start_time`, `end_time`, `completion_time`, `version`, `is_deleted`, `create_time`, `update_time`) VALUES ('a4b1bab115fb1ffcffce2c95ab4a1d94', '任务开发', '955f1c2097685c11be1feb231532e1c6', NULL, 'todo', 2, NULL, NULL, 12.0, '2025-02-26 16:02:48', '2025-02-27 16:02:48', NULL, 0, 0, '2025-02-26 16:02:50', '2025-02-26 16:02:50');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
