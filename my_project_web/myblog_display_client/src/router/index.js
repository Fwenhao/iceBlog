import Vue from 'vue'
import Router from 'vue-router'
import {LoadingBar} from 'iview'
import Index from '@/components/index/Index'

// 公共区域
// import CommonHeader from '@/components/header/CommonHeader'
import SimpleHeader from '@/components/header/SimpleHeader/SimpleHeader'
import CommonFooter from '@/components/footer/CommonFooter'

// 首页
import HomeContent from '@/components/content/HomeContent'
import ArticleContent from '@/components/content/ArticleContent'
import ArticleList from '@/components/content/ArticleList'
import CodeList from '@/components/content/CodeList'
import LifeList from '@/components/content/LifeList'
import TimeLineContent from '@/components/content/TimeLineContent'
import page404 from '@/components/views/404/page404'
import SearchResultContent from '@/components/content/SearchResultContent'
import EditTextContent from '@/components/content/EditTextContent'
import LoginContent from '@/components/content/LoginContent'
import RegistContent from '@/components/content/RegistContent'
import SaveTextContent from '@/components/content/SaveTextContent'

Vue.use(Router)

//获取原型对象上的push函数
const originalPush = Router.prototype.push
//修改原型对象中的push方法
Router.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}

const router = new Router({
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: [
    // eslint-disable-next-line no-undef
    // {
    //   path: '/404',
    //   component: page404,
    //   name: 'page404'
    // },
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [
        // 首页
        {
          path: '/',
          name: 'index',
          components: {
            header: SimpleHeader,
            content: HomeContent,
            footer: CommonFooter
          }
        },
        //登录页
        {
          path: '/login',
          name: 'login',
          components: {
            header: SimpleHeader,
            content: LoginContent,
            footer: CommonFooter
          }
        },
        //注册
        {
          path: '/regist',
          name: 'regist',
          components: {
            header: SimpleHeader,
            content: RegistContent,
            footer: CommonFooter
          }
        },
        // 全部文章列表
        {
          path: '/articleList',
          name: 'articleList',
          components: {
            header: SimpleHeader,
            content: ArticleList,
            footer: CommonFooter
          }
        },
        // 文章详情页
        {
          path: '/article/:articleId',
          name: 'article',
          components: {
            header: SimpleHeader,
            content: ArticleContent,
            footer: CommonFooter
          }
        },
        // 发表文章
        {
          path: '/publish/:managerId',
          name: 'publish',
          components: {
            header: SimpleHeader,
            content: SaveTextContent,
            footer: CommonFooter
          }
        },
        // 编辑文章
        {
          path: '/compile',
          name: 'compile',
          components: {
            header: SimpleHeader,
            content: EditTextContent,
            footer: CommonFooter
          }
        },
        // 文章查询页
        {
          path: '/articles/search',
          name: 'search',
          components: {
            header: SimpleHeader,
            content: SearchResultContent,
            footer: CommonFooter
          }
        },
        // 源码分享
        {
          path: '/codes',
          name: 'codes',
          components: {
            header: SimpleHeader,
            content: CodeList,
            footer: CommonFooter
          }
        },
        // {
        //   path: '/articles/category/:id',
        //   name: 'articles/category',
        //   components: {
        //     header: SimpleHeader,
        //     content: ArticleListContent,
        //     footer: CommonFooter
        //   }
        // },

        // 程序人生
        {
          path: '/life',
          name: 'life',
          components: {
            header: SimpleHeader,
            content: LifeList,
            footer: CommonFooter
          }
        },
        {
          path: '/timeline',
          name: 'timeline',
          components: {
            header: SimpleHeader,
            content: TimeLineContent,
            footer: CommonFooter
          }
        },
        {
          path: '*', // 页面不存在的情况下会跳到404页面
          redirect: '/404',
          name: 'notFound',
          hidden: true
        }
      ]
    }
  ]
})

// 配置加载进度条
LoadingBar.config({
  color: '#5cb85c',
  failedColor: '#f0ad4e',
  height: 2
})

router.beforeEach((to, from, next) => {
  LoadingBar.start()
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

router.afterEach((to, from, next) => {
  LoadingBar.finish()
  window.scrollTo(0, 0)
})

export default router
