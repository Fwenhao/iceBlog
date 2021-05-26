<template>
  <div class="article-list-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <div class="layout-left">
           <section-title :mainTitle="'技术分享'" :subTitle="'Articles'">
          </section-title>
          <article-list-cell v-for="article in articleList" :article="article" :key="article.keyId"></article-list-cell>
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
import Recommend from '@/components/views/Recommend'
import TagWall from '@/components/views/TagWall'
import ArticleListHeader from '@/components/views/Article/ArticleListHeader'
import ArticleListCell from '@/components/views/Article/ArticleListCell'
import SectionTitle from '@/components/views/SectionTitle/SectionTitle'
import TitleMenuFilter from '@/components/views/SectionTitle/TitleMenuFilter'
import {DefaultFilterList} from '@/common/js/const'

export default {
  data () {
    return {
      articleList: [],
      tagsColor: ['#D52BB3','#E6E61A','#3CC48D'],
      defaultFilterList: DefaultFilterList,
      currentPage: 1,
      pageSize: 5,
      total: 1,
      categoryId: this.$route.params.id,
      menuParams: {},
      noMoreData: false,
      imgUrl: 'https://file.iviewui.com/asd/live-1.jpg'
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
        articleName: "",
        pageSize: this.pageSize,
        current: this.currentPage
      }
      this.$axios.get('/search/getarticlebyname?articleName='+orderBy.articleName+'&pageSize='+orderBy.pageSize+'&current='+orderBy.current)
      .then(({data}) => {
        if (data.data !== null && data.code === 1) {
          this.articleList = data.data.records
          this.total = data.data.total
        }
      })
    },
    getTagsColor (index) {
      return this.tagsColor[index]
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
    'recommend': Recommend,
    'tag-wall': TagWall,
    'article-list-header': ArticleListHeader,
    'article-list-cell': ArticleListCell,
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
  /*.live-bg{
    background-image:url({{this.imgUrl}})
  }*/
</style>
