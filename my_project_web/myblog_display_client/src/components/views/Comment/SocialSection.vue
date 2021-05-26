<template>
  <div class="social-section">
    <!-- <div id="gitalk-container" v-if="show"></div> -->
    <!-- <div id="SOHUCS" :postId="type+postId"></div> -->
    <!-- <Comment :commentList="commentList" :postId="postId" @on-comment="showCommentById"></Comment> -->

  </div>
</template>

<script type="text/ecmascript-6">
import Comment from '@/components/views/Comment/Comment'

export default {
  props: {
    type: String,
    postId: Number,
    commentList:Array
  },
  data () {
    return {
      show: null
    }
  },
  components: {
    'Comment': Comment
  },
  watch: {

  },
  mounted () {
    console.log(postId)
  },

}
</script>
