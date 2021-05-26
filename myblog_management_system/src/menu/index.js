import { uniqueId } from 'lodash'

/**
 * @description 给菜单数据补充上 path 字段
 * @description https://github.com/d2-projects/d2-admin/issues/209
 * @param {Array} menu 原始的菜单数据
 */
function supplementPath (menu) {
  return menu.map(e => ({
    ...e,
    path: e.path || uniqueId('d2-menu-empty-'),
    ...e.children ? {
      children: supplementPath(e.children)
    } : {}
  }))
}

export const menuHeader = supplementPath([
  { path: '/index', title: '首页', icon: 'home' },
  {
    title: '用户',
    icon: 'folder-o',
    children: [
      { path: '/userHistory', title: '账号操作记录' },
      { path: '/userInfo', title: '用户详情' }
    ]
  },
  {
    title: '博客管理',
    icon: 'folder-o',
    children: [
      { path: '/articleHistory', title: '浏览记录' },
      { path: '/articleInfo', title: '博客管理' },
      { path: '/articleCollect', title: '收藏管理' }
    ]
  },
  {
    title: '评论回复管理',
    icon: 'folder-o',
    children: [
      { path: '/comment', title: '我的评论' },
      { path: '/reply', title: '我的回复' }
    ]
  }
])

export const menuAside = supplementPath([
  { path: '/index', title: '首页', icon: 'home' },
  {
    title: '用户',
    icon: 'folder-o',
    children: [
      { path: '/userHistory', title: '账号操作记录' },
      { path: '/userInfo', title: '用户详情' }
    ]
  },
  {
    title: '博客管理',
    icon: 'folder-o',
    children: [
      { path: '/articleHistory', title: '浏览记录' },
      { path: '/articleInfo', title: '博客管理' },
      { path: '/articleCollect', title: '收藏管理' }
    ]
  },
  {
    title: '评论回复管理',
    icon: 'folder-o',
    children: [
      { path: '/comment', title: '我的评论' },
      { path: '/reply', title: '我的回复' }
    ]
  }
])
