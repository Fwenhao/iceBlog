<template>
  <div class="archive-list-cell">
    <div class="info" >
      <iv-tag class="time-tag">{{post.createTime | formatDate}}</iv-tag>
      <div class="base-info">
        <router-link :to="{name:'article',params:{articleId:post.keyId}}">
          {{post.title}}
          </router-link>
        <a class="see-desc" @click.stop="seeDesc" :class="{show: showDesc}">查看简介&nbsp;&nbsp;<iv-icon type="android-arrow-dropup-circle"></iv-icon></a>
        <p class="desc" :class="{show: showDesc}">{{post.description}}</p>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import {mixin} from '@/utils'
import {dateFilter} from '../../../utils/date'

export default {
  filters: {
        formatDate(time) {
            var date = new Date(time);
            return dateFilter(date);
        }
  },
  name: 'archive-list-cell',
  props: {
    post: {
      Type: Object
    }
  },
  mixins: [mixin],
  data () {
    return {
      showDesc: false
    }
  },
  methods: {
    seeDesc () {
      this.showDesc = !this.showDesc
    }
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import "../../../common/stylus/theme.styl"

  .archive-list-cell
    position relative
    padding 5px 0
    &::after
      content ''
      position absolute
      min-height 25px
      top 0
      bottom 0
      left 88px
      width 4px
      background $default-link-hover-color
    .info
      display flex
      font-size 17px
      line-height 25px
      margin-left 100px
      cursor pointer
      @media only screen and (max-width: 720px)
        font-size 13px
      .time-tag
        flex-shrink 0
      .base-info
        a.title
          margin-left 25px
          color $default-title-color
          cursor pointer
          &:hover
            color $default-title-hover-color
        a.see-desc
          color $default-desc-color
          font-size 13px
          margin-left 5px
          font-weight 100
          cursor pointer
          > i
            transition: All 0.4s ease-in-out
            transform rotateX(180deg)
          &:hover,&.show
            color $default-desc-hover-color
          &.show
            > i
              transform rotateX(0deg)
        .desc
          font-size 14px
          font-weight 300
          margin-left 5px
          color $default-desc-color
          overflow hidden
          max-height 0
          transition All 0.4s ease-in-out
          &.show
            max-height 250px
</style>
