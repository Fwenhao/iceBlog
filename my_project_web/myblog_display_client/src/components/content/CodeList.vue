<template>
  <div class="article-list-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
          <section-title :mainTitle="'源码分享'" :subTitle="'Code'">
            <title-menu-filter @filterByMenu="listArticle"  slot="menu" :menu-filter-list="defaultFilterList"></title-menu-filter>
          </section-title>
          <article-list-cell v-for="article in articleList" :article="article" :key="article.id"></article-list-cell>
          <iv-page class='mt-10 text-right' :total="total" :current='currentPage' :page-size='pageSize' @on-change="changePage" @on-page-size-change="changeSize" show-elevator show-total/>
        </div>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <recommend></recommend>
          <tag-wall style="margin-top: 15px;"></tag-wall>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import ArticleListHeader from '@/components/views/Article/ArticleListHeader'
import ArticlePageContent from '@/components/views/Article/ArticlePageContent'
import ArticlePageFooter from '@/components/views/Article/ArticlePageFooter'
import ArticleListCell from '@/components/views/Article/ArticleListCell'
import Recommend from '@/components/views/Recommend'
import TagWall from '@/components/views/TagWall'
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
import TitleMenuFilter from '@/components/views/SectionTitle/TitleMenuFilter'
import merge from 'lodash/merge'
import {DefaultFilterList} from '@/common/js/const'

export default {
  data () {
    return {
      articleList: [],
      categoryList: [],
      currentPage: 1,
      pageSize: 5,
      categoryId: this.$route.params.id,
      defaultFilterList: DefaultFilterList
    }
  },
  created () {
    let param = {}
    param.latest = true
    this.listArticle(param)
  },
  methods: {
    listArticle (param) {
      let orderBy = {
        articleType: 1002,
        pageSize: this.pageSize,
        currentPage: this.currentPage
      }
      let params = merge(param, orderBy)
      this.$http({
        url: this.$http.adornUrl('/article/list'),
        method: 'get',
        params: this.$http.adornParams(params)
      }).then(({data}) => {
        if (data.result.data !== null && data.status === 0) {
          this.articleList = data.result.data.list
          this.total = data.result.data.total
        }
      })
    },
    changePage (page) {
      this.currentPage = page
      this.$router.push({path:this.$route.path,query:{
        latest: true,
        pageSize: 5,
        currentPage: this.currentPage
      }});
      this.listArticle()
    },
    changeSize (size) {
      this.pageSize = size
      this.currentPage = 1
      this.listArticle()
    },

  },
  components: {
    'article-list-header': ArticleListHeader,
    'article-page-content': ArticlePageContent,
    'article-page-footer': ArticlePageFooter,
    'article-list-cell': ArticleListCell,
    'recommend': Recommend,
    'tag-wall': TagWall,
    'section-title': SectionTitle,
    'title-menu-filter': TitleMenuFilter
  }
}
</script>

<style lang="stylus" rel="stylesheet/stylus">
  .article-list-content
    width auto
    min-height calc(100vh - 308px)
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
</style>
