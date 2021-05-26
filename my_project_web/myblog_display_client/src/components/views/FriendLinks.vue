<template>
  <div class="friend-links">
    <panel :title="'友情链接'">
      <!-- <ul class="link-list" slot="content">
        <li v-for="link in linkList" :key="link.id">
          <a :href="link.address" target="_blank">
            <img :src="link.avatar" alt="">
            <div class="right">
              <p class="title">{{link.name}}</p>
              <p class="link">{{link.address}}</p>
            </div>
          </a>
        </li>
      </ul> -->

      <div class="link-list" slot="content">
        <div v-for="link in linkList" :key="link.id">
          <a :href="link.address" target="_blank">
            <div class="right">
              <div class="title">{{link.name}}</div>
            </div>
          </a>
        </div>
      </div>
    </panel>
  </div>
</template>

<script type="text/ecmascript-6">
import Panel from '@/components/utils/Panel'

export default {
  data () {
    return {
      linkList: []
    }
  },
  created () {
    this.listLink()
  },
  components: {
    'panel': Panel
  },
  methods: {
    listLink () {
      this.$http({
        url: this.$http.adornUrl('/friend-address/getFriendAddress'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.status === 0) {
          this.linkList = data.result.data
          // console.log(JSON.stringify(this.linkList))
        }
      })
    }
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/theme.styl";

  .friend-links
    position relative
    div.link-list
      div
        display inline-block
        height 45px
        margin-right  10px
        a 
          float left
          display flex
          transform: translate(0, 0)
          transition: all 0.2s ease-in
          &:hover
            transform: translate(10px, 0)
            transition: all 0.2s ease-in-out
            .link
              color $dark
          .right
            display flex
            flex-direction column
            padding-left 12px
            height 30px
            line-height 60px
            div
              flex 1
              text-align left
              &.title
                height 35px
                line-height 45px
                font-size 17px
                color $color-main-primary
              &.link
                height 15px
                line-height 15px
                font-weight 100

</style>
