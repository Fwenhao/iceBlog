<template>
  <div class="comment-list-cell">

    <iv-row>
      <iv-col >
        <div id="comment">
         <article-content v-bind:article="article"></article-content>
         <commemt-content v-bind:comment="comment" v-on:change="changCommmer"></commemt-content>
         <comment-textarea v-bind:type="type" v-bind:name="oldComment" v-on:submit="addComment" v-on:canel="canelCommit"></comment-textarea>
        </div>

      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">


<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl";

  .comment-list-cell
    position relative
    text-align left
    .avatar
      img
        border-radius $border-radius
        width 100%
    .content
      margin 5px 0 8px
      .title
        width 800px
        font-size 0
        margin-bottom 5px
        line-height 18px
        .name
            font-size 15px
            color $color-main-primary
            width 800px
            word-wrap:break-word;
            word-break:break-all;
  .name-tag
          font-size 12px
          background-color $color-secondary-info
          padding 2px 5px
          margin 0 5px
          color #fff
          border-radius $border-radius
        .reply-icon
          font-size 15px
          color $light
        .reply-name
          font-size 15px
          margin 0 5px
          a
            color $dark
            &:hover
              color $color-main-primary
              text-decoration underline

          &.dark-theme
            a
              color $color-gradually-gray-71
              &:hover
                color $color-secondary-warning
                text-decoration underline
        .time
          font-size 13px
          color $light
          margin-left 8px
      .comment-content
        font-size 16px
        line-height 24px
        &.dark-theme
          color $color-gradually-gray-71
    .operate-area
      margin-top 8px
      font-size 14px
      width 800px
      span
        margin-right 10px
      .iv-dropdown-link
        cursor pointer
      .like, .unlike
        color $light
        font-weight 700
      .reply
        cursor pointer
      &.dark-theme
        .iv-dropdown-link
          &:hover
            color $color-secondary-warning
        .reply
          a
            color $color-secondary-warning
    .comment-area
      margin-bottom 10px
      .reply-editor
        margin-top 15px
        height 200px
        transition height 0.7s
        &.spread
          height 300px
      p.comment-tip
        a
          font-size 14px
          &:hover
            color $color-main-primary
</style>
