<template>
    <div>
        <List item-layout="vertical" :split="false" size="small" >
            <ListItem v-for="item in replyComment" :key="item.keyId" class="reply1">
                <ListItem>
                    <Avatar shape="square" size="small" :src="item.avatar"></Avatar>&nbsp;<span
                        style="color: #2d9aff;">{{item.replierName}}&nbsp;</span>
                        回复:
                    &nbsp;<span style="color: #2d9aff;" >{{comment.replierName}}</span>:{{item.content}}

                    <a @click="toParent(comment,item)" style="margin-left: 30px;">
                        <Icon type="ios-chatbubbles-outline"/> 回复
                    </a>
                   <li style="margin-top: 10px;">

                    </li>
                </ListItem>
                <div v-if="item.replyComments" class="reply2">
                    <reply :replyComment="item.replyComments" :comment='item'
                           v-bind="$attrs" v-on="$listeners"></reply>
                </div>
            </ListItem>
        </List>
    </div>

</template>
<script>

    export default {
        name: 'reply',
        props: {
            comment: {},
            replyComment: Array,

        },
        data() {
            return {}
        },
        methods: {
            //获取父类的方法
            toParent(comment, item) {
                this.$emit('showCommentInput',comment, item);
                console.log("获取到的父类方法",item)
            },
            //格式化时间的方法
            dateFilter: function (input) {
                var d = new Date(input);
                var year = d.getFullYear();
                var month = d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : "" + (d.getMonth() + 1);
                var day = d.getDate() < 10 ? "0" + d.getDate() : "" + d.getDate();
                var hour = d.getHours() < 10 ? "0" + d.getHours() : "" + d.getHours();
                var minutes = d.getMinutes() < 10 ? "0" + d.getMinutes() : "" + d.getMinutes();
                var seconds = d.getSeconds() < 10 ? "0" + d.getSeconds() : "" + d.getSeconds();
                return (year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds);
            },
        }
    }
</script>

<style scoped>
    list,listitem {
            display: block;
        }
        .reply2 {
            margin-left: 0px;
            margin-top: 10px;
        }
        .reply1{
          display: block;
          /* margin-left: 20px; */
          padding-top: 5px;
        }
</style>
