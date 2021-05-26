<template>
  <div class="home-content">
    <iv-row style="position: relative">
      <iv-col :xs="500" :sm="500" :md="500" :lg="500">
        <div class="dev-sign-main ivu-card ivu-card-dis-hover ivu-card-shadow">
          <div class="ivu-card-body">
            <iv-form
              class="ivu-form ivu-form-label-top"
              ref="loginForm"
              :model="loginForm"
              :rules="rules"
              @keydown.enter.native="handleSubmit"
            >
              <div
                class="ivu-form-item ivu-form-item-required ivu-form-item-error"
              >
                <label class="ivu-form-item-label">用户名</label>
                <iv-formItem prop="userName">
                <iv-input
                  type="text"
                  placeholder="请填写你的用户名"
                  v-model="loginForm.userName"
                  clearable
                />
                </iv-formItem>
              </div>
              <div
                class="ivu-form-item ivu-form-item-required ivu-form-item-error"
              >
                <label class="ivu-form-item-label">密码</label>
                <iv-formItem prop="password">
                  <iv-input
                    type="password"
                    v-model="loginForm.password"
                    placeholder="请输入密码"
                    clearable
                  />
                </iv-formItem>
              </div>
            </iv-form>
            <div class="dev-sign-main-aside">
              <iv-button
                type="info"
                class="ivu-btn ivu-btn-success ivu-btn-long ivu-btn-large"
                @click="handleSubmit"
              >
                <i class="ivu-icon ivu-icon-md-log-in"></i>
                <span>登录</span>
              </iv-button>
              <span class="ivu-input-prefix">
                <i class="ivu-icon ivu-icon-ios-mail-outline"></i
              ></span>
              <div class="dev-sign-main-aside-tip">
                <p>
                  <router-link to="/recover" class="">忘记密码？</router-link>
                </p>
                <span class="ivu-input-prefix">
                  <i class="ivu-icon ivu-icon-ios-mail-outline"></i
                ></span>
                <p>
                  还没有账户？
                  <router-link to="/regist" class="">注册</router-link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import "vue-nav-tabs/themes/paper.css";
import qs from "qs"; // 字符串处理
// mixin
import { mixin } from "@/utils";

export default {
  props: {
    userNameRules: {
      type: Array,
      default: () => {
        return [{ required: true, message: "账号不能为空", trigger: "blur" }];
      },
    },
    passwordRules: {
      type: Array,
      default: () => {
        return [{ required: true, message: "密码不能为空", trigger: "blur" }];
      },
    },
  },
  inject: ["reload"],
  data() {
    return {
      loginForm: {
        userName: "",
        password: "",
      },
    };
  },
  mixins: [mixin],
  created() {
    // this.getBook()
  },
  computed: {
    rules() {
      return {
        userName: this.userNameRules,
        password: this.passwordRules,
      };
    },
  },
  methods: {
    handleSubmit() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          let userInfo = {
            userName: this.loginForm.userName,
            userPassword: this.loginForm.password,
          };

          this.$axios.post("/user/signinto", userInfo).then(({ data }) => {
            if (data.code === 1) {
              this.$Message.success("登录成功");
              // 组件中直接使用 来设置session,把用户人id存入session
              sessionStorage.setItem("userMessage", JSON.stringify(data.data));
              //跳到首页
              this.reload();
              this.$router.push({ path: "/" });
            } else {
              this.$Message.error(data.msg);
              return;
            }
          });
        }
      });
    },
  },
};
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
@import '../../common/stylus/theme.styl';
@import '../../common/stylus/article.styl';

.home-content {
  width: auto;
  min-height: calc(100vh - 108px);

  @media only screen and (max-width: 768px) {
    margin: 5px 5px 0 5px;
  }

  @media screen and (min-width: 768px) {
    margin: 10px 10px 0 10px;
  }

  @media screen and (min-width: 992px) {
    margin: 15px 35px 0 35px;
  }

  @media screen and (min-width: 1200px) {
    width: 1200px;
    margin: 15px auto 0;
    margin-bottom: 200px;
  }

  .layout-left, .layout-right {
    padding: 0;

    @media only screen and (max-width: 768px) {
      padding: 0;
    }

    @media screen and (min-width: 768px) {
      padding: 0;
    }

    @media screen and (min-width: 992px) {
      padding: 0 10px;
    }

    @media screen and (min-width: 1200px) {
      padding: 0 10px;
    }
  }
}

/* .live-bg{
  background-image:url({{this.imgUrl}})
} */
element.style {
}

.dev-sign-main {
  width: 400px;
  margin: 50px auto;
  padding: 0 16px;
}

.ivu-card-shadow {
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.1);
}

.ivu-card {
  background: #FAFAFA;
  border-radius: 4px;
  font-size: 14px;
  position: relative;
  transition: all 0.2s ease-in-out;
}

article, aside, blockquote, body, button, dd, details, div, dl, dt, fieldset, figcaption, figure, footer, form, h1, h2, h3, h4, h5, h6, header, hgroup, hr, iv-input, legend, li, menu, nav, ol, p, section, td, textarea, th, ul {
  margin: 0;
  padding: 0;
}

*, :after, :before {
  box-sizing: border-box;
}

* {
  -webkit-tap-highlight-color: transparent;
}

user agent stylesheet, div {
  display: block;
}

* {
  -webkit-tap-highlight-color: transparent;
}

* {
  -webkit-tap-highlight-color: transparent;
}

* {
  -webkit-tap-highlight-color: transparent;
}

* {
  -webkit-tap-highlight-color: transparent;
}

body {
  font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, \\ 5FAE \8 F6F \9 6C 5 \9 ED1, Arial, sans-serif;
  font-size: 12px;
  line-height: 1.5;
  color: #515a6e;
  background-color: #fff;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

* {
  -webkit-tap-highlight-color: transparent;
}

html {
  font-family: sans-serif;
  line-height: 1.15;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
}

* {
  -webkit-tap-highlight-color: transparent;
}

*, :after, :before {
  box-sizing: border-box;
}

*, :after, :before {
  box-sizing: border-box;
}

.lizi {
  position: absolute;
  top: 0;
  left: 0;
  height: calc(100VH - 66px);
  width: 100VW;
  z-index: -1;
}

html, body {
  width: 100%;
}
</style>
