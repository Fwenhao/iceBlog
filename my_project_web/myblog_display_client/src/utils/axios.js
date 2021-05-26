import axios from 'axios'
//这里配置了请求URL和响应超时时间
axios.defaults.baseURL = 'http://fyyice.cn:8089'
axios.defaults.timeout = 30 * 1000


// 请求拦截器，在请求头里添加TOKEN
axios.interceptors.request.use(config => {
  console.log("config",config)
  console.log("userMessage",JSON.parse(sessionStorage.getItem("userMessage")))

    //放行过滤token 需要参数的接口
    const doNotRequireParamsURL = ["/search/getRound","/article/notices"]
    //放行过滤token 不需要参数的
    const requireParamsURL = ["/search/getarticlebyname","/comment/getlist"]
    if(sessionStorage.getItem("userMessage") == null || requireParamsURL.indexOf(config.url) === -1 || doNotRequireParamsURL.indexOf(config.url) === 1){
      config.headers["token"] = ""
    }else{
      config.headers["token"] = JSON.parse(sessionStorage.getItem("userMessage")).token
    }

    config.headers.post["Content-Type"]="application/json;charset=UTF-8"

  return config
})

export default axios;