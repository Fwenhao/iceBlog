<template>
  <div class="about">
    <img class="background" src="../../assets/350.jpg" alt="">
    <img class="avatar" src="../../assets/头像.png" alt="">
    <p v-show="this.manager == null" class="name">Fwenhao</p>
    <p v-show="this.manager != null" class="name">{{manager.name}}</p>
    <p class="desc">随便说点啥吧。</p>
    <!-- <div class="line"></div> -->
    <ul class="social">
      <li>
        <div style="float: left;margin-top: 0px" class="desc">社交:</div>
        <a href="http://wpa.qq.com/msgrd?v=3&uin=876811855&site=qq&menu=yes" target="_blank"><img src="../../assets/icon/QQ.png"  alt=""></a>
        <a href="https://github.com/Fwenhao" target="_blank"><img src="../../assets/icon/github.png" alt=""></a>
      </li>
    </ul>
    <div class="line"></div>
    <ul class="social">
      <li>
        <div style="float: left;margin-top: 0px" class="desc">加入我们:</div>
        <a href="//shang.qq.com/wpa/qunwpa?idkey=5342c70630733610e25122d3731f16a58a2db6c84ccb7861a736138685998e11" target="_blank" style="margin-left: -50px;width: 50px; "><img src="../../assets/icon/Qqun.png"  alt=""><span style="font-size: 5px">技术交流</span></a>
      </li>
    </ul>
    <h4>技能值</h4>
    <div class="progresses">
      <iv-row :gutter="20">
        <iv-col :span="6">
          <p class="title">Java</p>
        </iv-col>
        <iv-col :span="18">
          <iv-progress status="normal" :hide-info="true" :stroke-width="8" :percent="70" class="bar"></iv-progress>
        </iv-col>
      </iv-row>
      <iv-row :gutter="20">
        <iv-col :span="6">
          <p class="title">Spring</p>
        </iv-col>
        <iv-col :span="18">
          <iv-progress status="wrong" :hide-info="true" :stroke-width="8" :percent="60" class="bar"></iv-progress>
        </iv-col>
      </iv-row>
      <iv-row :gutter="20">
        <iv-col :span="6">
          <p class="title">Vue</p>
        </iv-col>
        <iv-col :span="18">
          <iv-progress status="success" :hide-info="true" :stroke-width="8" :percent="20" class="bar"></iv-progress>
        </iv-col>
      </iv-row>
      <iv-row :gutter="20">
        <iv-col :span="6">
          <p class="title">Cloud</p>
        </iv-col>
        <iv-col :span="18">
          <iv-progress status="active" :hide-info="true" :stroke-width="8" :percent="30" class="bar"></iv-progress>
        </iv-col>
      </iv-row>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  data () {
    return {
      manager:{
        name:''
      },
    }
  },
  mounted: function () {
    let manager = JSON.parse(sessionStorage.getItem('userMessage'))
    if (manager !== null){
      this.manager = manager;
    } else {
      this.manager = this.manager;
    }
  },
}
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
  @import "../../common/stylus/theme.styl";

  .about
    position relative
    text-align center
    border 1px solid $color-border
    padding-bottom 20px
    background-color #fff
    img.background
      position absolute
      top 0
      left 0
      right 0
      width 100%
      height 125px
      z-index 1
    img.avatar
      position relative
      margin 75px auto 15px
      width 100px
      height 100px
      border 5px solid $color-gradually-gray-91
      border-radius 50%
      z-index 9
    .name
      font-size 22px
      color $color-typegraphy-title
      line-height 30px
      font-weight 700
    .desc
      font-size 15px
      color $color-secondary-info
      line-height 30px
      font-weight 100
    .social
      text-align center
      padding 0 20px
      margin-top 15px
      > li
        padding 8px
        a
          display inline-block
          width: 44px
          height: 44px
          margin: auto
          img
            width 90%
    .line
      height 1px
      background-color $color-gradually-gray-91
      margin 10px 20px
    h4
      font-size 19px
      margin 30px 0 20px
      font-weight 600
    .progresses
      padding 0 20px
      p.title
        height 38px
        line-height 38px
        text-align right
      .bar
        margin 10px 0
</style>
