<template>
  <div class="article-page-footer">
    <license-tag :tags ="tags"></license-tag>
    <article-pay></article-pay>
   <!-- <social-section :type="'Article'" :postId="postId" :commentList="commentList"></social-section> -->
    <Card title="评论区">
        <Comment :commentList="commentList" :postId="postId" @on-comment="showCommentById"></Comment>
    </Card>
    </br>
  </div>
</template>

<script type="text/ecmascript-6">
import LicenseTag from '@/components/views/LicenseTag'
import SocialSection from '@/components/views/Comment/SocialSection'
import Pay from '@/components/views/Pay'
import Comment from '@/components/views/Comment/Comment'

export default {
  data () {
    return {

    }
  },
  components: {
    'license-tag': LicenseTag,
    'article-pay':Pay,
    'social-section': SocialSection,
    'Comment':Comment
  },
  props: {
    postId: Number,
    tags: Array,
    commentList:Array
  },
   mounted() {

   }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .article-page-footer
    text-align left
    .operate_menu
      margin-top 30px
    .comment-menu
      margin-top 30px
      .comment-menu-item
        margin-bottom 20px
      p.comment-menu-item
        a
          font-size 14px
          &:hover
            color $color-main-primary
  .operate_info
    text-align right
    font-size 14px
    margin-left  15 0 0 0

</style>
