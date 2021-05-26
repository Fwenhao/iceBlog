import { find, assign } from 'lodash'

const users = [
  { username: 'admin', password: 'admin', uuid: 'admin-uuid', name: 'Admin' },
  { username: 'editor', password: 'editor', uuid: 'editor-uuid', name: 'Editor' },
  { username: 'user1', password: 'user1', uuid: 'user1-uuid', name: 'User1' }
]

export default ({ request }) => ({
  /**
   * @description 登录
   * @param {Object} data 登录携带的信息
   */
  SYS_USER_LOGIN(data) {
    return request({
      url: '/user/signinto',
      method: 'post',
      data
    })
  },
  /**
   * 获取用户浏览记录
   */
  getBrowserRecord(params) {
    return request({
      url: '/article/browserecords/get',
      method: 'get',
      params
    })
  },
  /**
   * 获取作者的文章列表
   */
  getArticleByAuthor(params) {
    return request({
      url: '/article/get/author',
      method: 'get',
      params
    })
  },
  /**
   * 删除博客
   * @param {articleId} params 
   * @returns 
   */
  deleteArticle(params) {
    return request({
      url: '/article/delete',
      method: 'delete',
      params
    })
  },

  /**
   * 查询文章
   */
  search(params) {
    return request({
      url: '/search/getarticlebyname',
      method: 'get',
      params
    })
  },

  /**
   * 获取用户账号操作记录
   * @param {*} params 
   * @returns 
   */
  getAccountHistory(params) {
    return request({
      url: '/user/getHistory',
      method: 'get',
      params
    })
  },

  /**
   * 获取用户评论
   * @param {*} params 
   * @returns 
   */
  getCommentByUser(params) {
    return request({
      url: '/comment/getByUser',
      method: 'get',
      params
    })
  },

  /**
   * 删除评论
   * @param {*} params 
   * @returns 
   */
  deleteComment(params) {
    return request({
      url: '/comment/delete',
      method: 'delete',
      params
    })
  },

  /**
   * 获取回复
   * @param {*}} params 
   * @returns 
   */
  getReplyByUser(params) {
    return request({
      url: '/comment/reply/get',
      method: 'get',
      params
    })
  },

  /**
   * 删除回复
   * @param {*} params 
   * @returns 
   */
  deleteReply(params) {
    return request({
      url: '/comment/reply/delete',
      method: 'delete',
      params
    })
  },

  /**
   * 获取文章收藏列表
   * @param {*} params 
   * @returns 
   */
  getCollect(params) {
    return request({
      url: '/collect/getlist',
      method: 'get',
      params
    })
  },

  /**
   * 删除收藏
   * @param {*} params 
   * @returns 
   */
  deleteCollect(params) {
    return request({
      url: '/collect/delete',
      method: 'delete',
      params
    })
  },
  
  /**
   * 用户更新
   * @param {*} data 
   * @returns 
   */
  updateUser(data) {
    return request({
      url: '/user/updateInfo',
      method: 'put',
      data
    })
  },
  
  /**
   * 获取当前用户信息详情
   * @param {*} params 
   * @returns 
   */
  getUserInfo(params) {
    return request({
      url: '/user/getLocalInfo',
      method: 'post',
      params
    })
  },
  
  layout(params) {
    return request({
      url: '/user/logout',
      method: 'post',
      params
    })
  },
})
