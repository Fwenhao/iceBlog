<template>
  <div class="article-page-header">
    <div class="tags">
      <iv-tag :color="index | mapTagColor" v-for="(tag , index) in article.tags" :key="tag.id">{{tag}}</iv-tag>
    </div>
    <p class="title">{{article.title}}</p>
    <iv-row>
      <iv-col :xs="24" :sm="10" :md="10" :lg="10" style="padding-left: 0;padding-right: 0;">
        <p class="info"><span class="author"><iv-icon type="ios-contact" class="icon"/><a href="">{{article.author}}</a></span>
          <span class="publish-time">  <iv-icon type="ios-timer-outline" class="icon"/>{{article.createTime | formatDate}}</span></p>
      </iv-col>
      <iv-col :xs="24" :sm="14" :md="14" :lg="14" style="padding-left: 0;padding-right: 0;">
        <p class="operate_info">
          <span><a><iv-icon type="eye"></iv-icon>{{article.vcount}} 阅读</a></span>
          <span><a @click="collect(article)"><iv-icon type="heart"></iv-icon> 收藏</a></span>
          <span v-show="manager.userInfo.userName===article.author"><router-link :to="{path:'/compile',query:{managerId:manager.keyId,articleId:article.keyId}}"><iv-icon type="compose"></iv-icon>详情编辑</router-link></span>
        </p>
      </iv-col>
    </iv-row>
    <!-- <p class="abstract" v-if="article.enclosure">
      附件：<a @click="handlePreview(article.enclosure)">点击下载</a>
    </p> -->
  </div>
</template>

<script type="text/ecmascript-6">
import { mixin } from '@/utils'
import {dateFilter} from '../../../utils/date'

export default {
  filters: {
        formatDate(time) {
            var date = new Date(time);
            return dateFilter(date);
        }
  },
  props: {
    article: {}
  },
  data () {
    return {
      manager:{}
    }
  },
  mixins: [mixin],
  methods: {
    collect (post) {
      this.$axios.post('/collect/add?articleId=' + post.keyId)
      .then(({data}) => {
        if (data && data.code === 1) {
          this.$Notice.success({
            title: '系统提示',
            desc: data.msg
          })
        }else{
          this.$Notice.error({
            title: '系统提示',
            desc: data.msg
          })
        }
      }).catch((error) => {
        console.log(error)
      })
    },

    handlePreview(url) {
        var link = document.createElement('a');
        try {
          link.href  = url;
        } catch (error) {
          link.href  = window.URL.createObjectURL(url);}
          link.click();
      },
  },
  mounted: function () {
    let manager = JSON.parse(sessionStorage.getItem('userMessage'))
    console.log(manager)
    if (manager !== null){
      this.manager = manager;
    } else {
      this.manager = this.manager;
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/index.styl";

  .article-page-header
    text-align left
    padding 25px 5px 10px 5px
    @media only screen and (max-width: 768px)
      padding-top 10px
    @media screen and (min-width: 768px)
      padding-top 10px
    @media screen and (min-width: 992px)
      padding-top 25px
    .tags
      margin-bottom 18px
    .title
      font-size 27px
      line-height 33px
      font-weight 500
      color $color-typegraphy-title
      margin-bottom 23px
      text-align center
    .info
      margin-top 10px
      font-size 14px
      line-height 18px
      font-weight 200
      a
        color: #777
        cursor pointer
        &:hover
          color $color-main-primary
          text-decoration underline
      .publish-time
        margin-left 20px
    .operate_info
      text-align right
      font-size 14px
      margin 15px 0
      @media only screen and (max-width: 768px)
        text-align left
      span
        margin-right 10px
        + span
          margin-left 10px
        a
          cursor pointer
          &:hover
            color $color-main-primary
            text-decoration underline
    .abstract
      color $default-desc-color
      border 1px dashed $default-border-color
      background $default-border-color
      @media only screen and (max-width: $responsive-sm)
        padding 8px
        margin-top 8px
        font-size 13px
        line-height 18px
      @media screen and (min-width: $responsive-sm)
        padding 15px
        margin-top 8px
        font-size 14px
        line-height 24px
      @media screen and (min-width: $responsive-md)
        padding 20px
        margin-top 15px
        line-height 28px
      @media screen and (min-width: $responsive-lg)
        padding 20px
        margin-top 15px
        line-height 28px
  .icon{
    margin-right: 5px;
  }
</style>
