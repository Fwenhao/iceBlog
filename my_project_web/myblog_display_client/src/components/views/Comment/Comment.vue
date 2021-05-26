<template>
  <div>
    <div id="target">
      <iv-input
        v-model="inputComment"
        type="textarea"
        :rows="3"
        :placeholder="placeholders"
      >
      </iv-input>
      <div style="text-align: right; margin-top: 10px">
        <iv-button @click="cancel" style="margin-right: 10px">取消</iv-button>
        <iv-button type="primary" round @click="commitComment">确定</iv-button>
      </div>
    </div>

    <List item-layout="vertical" size="small" :split="false">
      <ListItem v-for="item in commentList" :key="item.id">
        <ListItem>
          <li
            style="margin-left: 0px"
            v-show="item !== '' || item.content !== null"
            @click="showCommentInput(comment, item)"
          >
            <span style="color: #2d9aff">{{ item.comment.userName }} </span>：{{
              item.comment.content
            }}
            <a style="margin-left: 30px">
              <Icon type="ios-chatbubbles-outline" /> 回复
            </a>
          </li>
          <!--  -->
        </ListItem>
        <div v-if="item.replyList" class="commentSecond">
          <Reply
            :replyComment="item.replyList"
            :comment="item"
            v-on:showCommentInput="showCommentInput"
          ></Reply>
        </div>
      </ListItem>
    </List>
  </div>
</template>

<script>
import Reply from "./Reply";

export default {
  components: {
    Reply,
  },
  name: "Comment",
  props: {
    commentList: Array,
    postId: Number,
  },
  data() {
    return {
      //评论内容
      inputComment: "",
      //输入框默认内容
      placeholders: "写下你的评论",
    };
  },

  methods: {
    /**
     * 点击取消按钮
     */
    cancel() {
      this.inputComment = "";
      this.placeholders = "写下你的评论";
      this.fromId = null;
    },
    /**
     * 提交评论
     */
    async commitComment() {
      const userMessage = JSON.parse(sessionStorage.getItem("userMessage"));
      if (userMessage != null) {
        scrollTo(0, 0);
        if (!this.inputComment) {
          this.$Notice.warning({
            title: "提交",
            desc: "评论信息不能为空！",
          });
        } else {
          const commentDTO = {
            content: this.inputComment,
            articleId: this.postId,
          };
          this.$axios.post("/comment/create", commentDTO).then(({ data }) => {
            if (data.code === 1) {
              this.$Notice.success({
                title: "提示",
                desc: data.msg,
              });
              this.$emit("on-comment", this.postId);
              location.reload();
              return;
            } else {
              this.$Notice.error({
                title: "提示",
                desc: data.msg,
              });
            }
          });
        }
      } else {
        this.$Message.error("请先登录后再进行评论");
        this.$router.push("/login");
      }

      this.placeholders = "写下你的评论";
      this.inputComment = "";
    },

    showCommentInput(item, reply) {
      if (reply) {
        this.$prompt("请输入邮箱", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        })
          .then(({ action,value }) => {
            if(action === 'confirm'){
              if(value != null && value != ""){
                const commentReplyDTO = {
                  articleId: reply.comment.articleId,
                  commentId: reply.comment.keyId,
                  content: value,
                  reviewerId: reply.comment.userId,
                  reviewerName: reply.comment.userName
                }
                this.$axios.post('/comment/reply/insert',commentReplyDTO).then((res) => {
                  if(res){
                    this.$message.success(res.msg)
                    
                  }else{
                    this.$message.error(res.msg)
                  }
                })
              }
            }
            this.$message({
              type: "success",
              message: "您的评论内容: " + value,
            });
            location.reload();
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "取消输入",
            });
          });

      } else {
        this.inputComment = "";
      }
      //当点击了回复时，页面滚动到评论框的位置
      document.getElementById("target").scrollIntoView();
      this.fromId = reply.keyId;
    },
    //格式化时间的方法
    dateFilter: function (input) {
      var d = new Date(input);
      var year = d.getFullYear();
      var month =
        d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : "" + (d.getMonth() + 1);
      var day = d.getDate() < 10 ? "0" + d.getDate() : "" + d.getDate();
      var hour = d.getHours() < 10 ? "0" + d.getHours() : "" + d.getHours();
      var minutes =
        d.getMinutes() < 10 ? "0" + d.getMinutes() : "" + d.getMinutes();
      var seconds =
        d.getSeconds() < 10 ? "0" + d.getSeconds() : "" + d.getSeconds();
      return (
        year +
        "-" +
        month +
        "-" +
        day +
        " " +
        hour +
        ":" +
        minutes +
        ":" +
        seconds
      );
    },
  },
};
</script>

<style scoped>
.commentSecond {
  padding: 2px;
  /* background-color: #EAEAEC; */
  border-radius: 5px;
  margin: 2px 10px;
}
list,
listitem {
  display: block;
}
</style>
