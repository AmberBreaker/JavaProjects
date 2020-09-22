package com.shrm.service;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 现有这么一个使用场景，用户做完一个课程的习题后有一个习题情况记录列表，本地已有用户该课程所有层级结构状态记录，
 * 请完成代码更新本地用户该课程层级结构 每个节点的完成习题数、 正确习题数及 掌握知识点数。
 *
 * <p>
 * 该课程结构是一个树状结构，知识点为此树状结构的叶子节点，(为方便理解以下称为树状结构)
 * 同一个知识点可以同时在不同的非叶子节点下，知识点关联了习题，
 * 并且一个习题可能关联多个知识点，一个知识点也可以关联多个习题，即多对多关系。
 * 习题答题记录数据结构中的parents是这个习题关联的所有知识点的父id集合的去重集合。
 * </p>
 * <pre>
 * 例子:
 * 政治(id=1)
 *   ├── 马原理(id=2)
 *   │   ├── 知识点1(id=4)
 *   │   └── 知识点2(id=5)
 *   └── 毛中特(id=3)
 *       ├── 毛特(id=6)
 *       │   ├── 知识点1(id=4)
 *       │   ├── 知识点3(id=7)
 *       │   └── 知识点4(id=8)
 *       └── 中特(id=9)
 *           ├── 知识点1(id=4)
 *           └── 知识点5(id=10)
 *
 * 如有习题1关联知识点1和知识点5, 其parents="4,2,1,6,3,10,9";
 * 如有习题2关联知识点2和知识点3, 其parents="5,2,1,7,6,3";
 *
 * </pre>
 *
 * 完成习题数: 树状课程结构节点完成的习题个数。</br>
 * 正确习题数: 树状课程结构节点正确的习题个数。</br>
 * 掌握知识点数: 树状课程结构节点掌握的知识点个数(知识点正确习题数大于所有习题数的60%即为掌握)。</br>
 *
 * <p>已有两个方法获取树状结构id是否是知识点、树状结构节点关联的总题数和本地已有保存的用户树状结构记录</p>
 *
 * @see #getCourseNodes(long[] ids) 获取树状结构id是否是知识点、树状结构节点关联的总题数
 * @see #getLocalUserCourseRecords(long[] ids) 返回本地已有保存的用户树状结构记录
 * @see CourseNode 树状课程结构节点数据结构
 * @see AnswerRecord 习题答题记录数据结构
 * @see UserCourseRecord 需要更新的树状结构的数据结构
 * @see #getUserCourseRecords 需要完成的方法
 */
public class UserExerciseRecord {

    /**
     * 请完成以下方法，实现以上功能
     *
     * @param records 答题记录集合
     */
    private List<UserCourseRecord> getUserCourseRecords(List<AnswerRecord> records) {
        //TODO 需要编写的逻辑
        List<UserCourseRecord> record = new ArrayList<>();

        Map<String, UserCourseRecord> tmp = new HashMap<>();

        for (AnswerRecord answerRecord : records) {
            String parentIds = answerRecord.parents;
            String[] parentIdStrArr = parentIds.split(",");
            long[] parentIdArr = new long[parentIdStrArr.length];
            for (int i = 0; i < parentIdStrArr.length; i++) {
                parentIdArr[i] = Long.valueOf(parentIdStrArr[i]);
            }

            List<UserCourseRecord> localUserCourseRecords = getLocalUserCourseRecords(parentIdArr);
            for (UserCourseRecord localRecord : localUserCourseRecords) {

            }

            List<CourseNode> courseNodes = getCourseNodes(parentIdArr);
            for (CourseNode course : courseNodes) {

            }
        }
        return record;
    }

    /**
     * 树状结构(parents的中某个id)课程节点是否知识点及总题数 (无需编写这是已有方法)
     *
     * @param ids 树状结构id
     */
    private List<CourseNode> getCourseNodes(long[] ids) {
        // 无需编写这是已有方法
        return new ArrayList<>();
    }

    /**
     * 本地已有保存的用户树状结构记录 (无需编写这是已有方法)
     *
     * @param ids 树状结构id
     */
    private List<UserCourseRecord> getLocalUserCourseRecords(long[] ids) {
        // 无需编写这是已有方法
        return new ArrayList<>();
    }

    /**
     * 习题答题记录数据结构
     */
    @Getter
    @Setter
    static class AnswerRecord {

        /**
         * 习题id
         */
        long id;
        /**
         * 这次答题是否正确
         */
        boolean correct;
        /**
         * 习题相关知识点及课程结构列表以逗号(,)分隔
         * parents字段是一个树状课程结构的id, 并且叶子定义为知识点, 习题可能关联多个知识点，parents是习题所有相关知识点id及其相关父节点id的去重集合
         */
        String parents;
        /**
         * 上次答题情况，-1从未做过，0上次做错，1上次正确
         */
        int last;

    }

    /**
     * 树状课程结构节点数据结构
     */
    @Getter
    @Setter
    static class CourseNode {

        /**
         * 节点id
         */
        long id;
        /**
         * 是否是知识点
         */
        boolean knowledge;
        /**
         * 节点拥有习题数
         */
        int exercisesCount;


    }

    /**
     * 需要更新课程结构的数据结构
     */
    @Getter
    @Setter
    static class UserCourseRecord {

        /**
         * 课程结构id
         */
        long id;
        /**
         * 完成习题数
         */
        int completed = 0;
        /**
         * 正确习题数
         */
        int correct = 0;
        /**
         * 掌握知识点数
         */
        int master = 0;
    }
}
