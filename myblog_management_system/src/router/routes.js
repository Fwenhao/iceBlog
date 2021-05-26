import layoutHeaderAside from '@/layout/header-aside'

// 由于懒加载页面太多的话会造成webpack热更新太慢，所以开发环境不使用懒加载，只有生产环境使用懒加载
const _import = require('@/libs/util.import.' + process.env.NODE_ENV)

/**
 * 在主框架内显示
 */
const frameIn = [
  {
    path: '/',
    redirect: { name: 'index' },
    component: layoutHeaderAside,
    children: [
      // 首页
      {
        path: 'index',
        name: 'index',
        meta: {
          auth: true
        },
        component: _import('system/index')
      },
      // 用户
      {
        path: 'userHistory',
        name: 'userHistory',
        meta: {
          title: '账号操作记录',
          auth: true
        },
        component: _import('user/history')
      },
      {
        path: 'userInfo',
        name: 'userInfo',
        meta: {
          title: '用户详情',
          auth: true
        },
        component: _import('user/info')
      },
      //  文章
      {
        path: 'articleHistory',
        name: 'articleHistory',
        meta: {
          title: '博客浏览记录',
          auth: true
        },
        component: _import('article/history')
      },
      {
        path: 'articleInfo',
        name: 'articleInfo',
        meta: {
          title: '我的博客管理',
          auth: true
        },
        component: _import('article/info')
      },
      {
        path: 'articleCollect',
        name: 'articleCollect',
        meta: {
          title: '收藏管理',
          auth: true
        },
        component: _import('article/collect')
      },
      //  评论回复
      {
        path: 'comment',
        name: 'comment',
        meta: {
          title: '我的评论',
          auth: true
        },
        component: _import('comment/commentinfo')
      },
      {
        path: 'reply',
        name: 'reply',
        meta: {
          title: '我的回复',
          auth: true
        },
        component: _import('comment/replyinfo')
      },
      // 系统 前端日志
      {
        path: 'log',
        name: 'log',
        meta: {
          title: '前端日志',
          auth: true
        },
        component: _import('system/log')
      },
      // 刷新页面 必须保留
      {
        path: 'refresh',
        name: 'refresh',
        hidden: true,
        component: _import('system/function/refresh')
      },
      // 页面重定向 必须保留
      {
        path: 'redirect/:route*',
        name: 'redirect',
        hidden: true,
        component: _import('system/function/redirect')
      }
    ]
  }
]

/**
 * 在主框架之外显示
 */
const frameOut = [
  // 登录
  {
    path: '/login',
    name: 'login',
    component: _import('system/login')
  }
]

/**
 * 错误页面
 */
const errorPage = [
  {
    path: '*',
    name: '404',
    component: _import('system/error/404')
  }
]

// 导出需要显示菜单的
export const frameInRoutes = frameIn

// 重新组织后导出
export default [
  ...frameIn,
  ...frameOut,
  ...errorPage
]
