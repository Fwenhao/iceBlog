<template>
  <div class="home-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <photo-wall></photo-wall>
          <section-title :mainTitle="'通知'" :subTitle="'Notices'">
          </section-title>
          <article-list-cell v-for="article in articleList" :article="article" :key="article.title" :type="'article'"></article-list-cell>
          <iv-page class='mt-10 text-right' :total="total" :current='pageParam.currentPage' :page-size='pageParam.pageSize' @on-change="changePage" @on-page-size-change="changeSize" show-elevator show-total/>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <about></about>
          <recommend></recommend>
          <tag-wall style="margin-top: 15px;"></tag-wall>
          <friend-links style="margin-top:15px;"></friend-links>
        </div>
        <div class="layout-left">
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import PhotoWall from '@/components/views/PhotoWall'
import ArticleListCell from '@/components/views/Article/ArticleListCell'
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
import TitleMenuFilter from '@/components/views/SectionTitle/TitleMenuFilter'
import ArticlePageHeader from '@/components/views/Article/ArticlePageHeader'
import ArticlePageContent from '@/components/views/Article/ArticlePageContent'
import ArchiveListTimeTitle from '@/components/views/Archive/ArchiveListTimeTitle'
import ArchiveListCell from '@/components/views/Archive/ArchiveListCell'
import About from '@/components/views/About'
import FriendLinks from '@/components/views/FriendLinks'
import TagWall from '@/components/views/TagWall'
import Recommend from '@/components/views/Recommend'
import HotRead from '@/components/views/HotRead'
import SideToc from '@/components/views/SideToc'
import merge from 'lodash/merge' // 合并对象工具
import {DefaultFilterList, DefaultLimitSize} from '@/common/js/const'

export default {
  data () {
    return {
      articleList: [],
      manager:{},
      defaultFilterList: DefaultFilterList,
      total: 1,
      pageParam: {
        pageSize: DefaultLimitSize,
        currentPage: 1
      }
    }
  },
  components: {
    'photo-wall': PhotoWall,
    'article-list-cell': ArticleListCell,
    'section-title': SectionTitle,
    'title-menu-filter': TitleMenuFilter,
    'article-page-header': ArticlePageHeader,
    'article-page-content': ArticlePageContent,
    'archive-list-time-title': ArchiveListTimeTitle,
    'archive-list-cell': ArchiveListCell,
    'about': About,
    'friend-links': FriendLinks,
    'side-toc': SideToc,
    'tag-wall': TagWall,
    'recommend': Recommend,
    'hot-read': HotRead
  },
  created: function () {
    let param = {}
    param.latest = true
    this.refreshArticle(param)
    // this.hello()
  },
  mounted: function () {
    let manager = JSON.parse(sessionStorage.getItem('currentManager'))
    if (manager !== null){
      this.manager = manager;
    } else {
      this.manager = this.manager;
    }
  },
  methods: {
    refreshArticle (param) {
      this.$axios.get('/article/notices')
      .then(({data}) => {
        if (data.data !== null && data.code === 1) {
          this.articleList = data.data.records
          this.total = data.data.total
        }
      })
    },
    handleMove (liveModel) {
      liveModel.setParamFloat('PARAM_ANGLE_X', 1)
    },
    changePage (page) {
      this.pageParam.currentPage = page
      this.$router.push({path:this.$route.path,query:{
        latest: true,
        pageSize: 5,
        currentPage: this.pageParam.currentPage
      }});
      this.refreshArticle()
    },
    changeSize (size) {
      this.pageParam.pageSize = size
      this.pageParam.currentPage = 1
      this.refreshArticle()
    },
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .home-content
    width auto
    min-height calc(100vh - 108px)
    @media only screen and (max-width: 768px)
      margin 5px 5px 0 5px
    @media screen and (min-width: 768px)
      margin 10px 10px 0 10px
    @media screen and (min-width: 992px)
      margin 15px 35px 0 35px
    @media screen and (min-width: 1200px)
      width 1200px
      margin 15px auto 0
      margin-bottom 200px
    .layout-left, .layout-right
      padding 0
      @media only screen and (max-width: 768px)
        padding 0
      @media screen and (min-width: 768px)
        padding 0
      @media screen and (min-width: 992px)
        padding 0 10px
      @media screen and (min-width: 1200px)
        padding 0 10px
  /*.live-bg{
    background-image:url({{this.imgUrl}})
  }*/
</style>
