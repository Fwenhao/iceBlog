<template>
  <div class="home-content">
    <iv-row>
      <iv-col :xs="500" :sm="500" :md="500" :lg="500">
        <div class="dev-sign-main ivu-card ivu-card-dis-hover ivu-card-shadow">
          <div class="ivu-card-body">
            <iv-form
              autocomplete="off"
              class="ivu-form ivu-form-label-top"
              ref="registForm"
              :rules="registerRules"
              :model="form"
              @keydown.enter.native="regist('registForm')"
            >
              <div
                class="ivu-form-item ivu-form-item-required ivu-form-item-error"
              >
                <label class="ivu-form-item-label">用戶身份</label>
                <iv-formItem prop="identity">
                  <iv-select v-model="form.identity">
                    <iv-option
                      v-for="item in accountIdentity"
                      :value="item.value"
                      :key="item.value"
                      >{{ item.label }}</iv-option
                    >
                  </iv-select>
                </iv-formItem>
              </div>
              <div
                class="ivu-form-item ivu-form-item-required ivu-form-item-error"
              >
                <label class="ivu-form-item-label">用户名</label>
                <iv-formItem prop="username">
                  <iv-input
                    type="text"
                    placeholder="起一个拉风的名字吧"
                    v-model="form.username"
                  >
                  </iv-input>
                </iv-formItem>
              </div>
              <div
                class="ivu-form-item ivu-form-item-required ivu-form-item-error"
              >
                <label class="ivu-form-item-label">真实名</label>
                <iv-formItem prop="nickname">
                  <iv-input
                    type="text"
                    placeholder="您的真实名"
                    v-model="form.nickname"
                  >
                  </iv-input>
                </iv-formItem>
              </div>
              <div
                class="ivu-form-item ivu-form-item-required ivu-form-item-error"
              >
                <label class="ivu-form-item-label">电子邮箱</label>
                <iv-formItem prop="userEmail">
                  <iv-input
                    type="email"
                    placeholder="请填写你的电子邮箱"
                    v-model="form.userEmail"
                  >
                    <iv-button
                      style="height: 30px"
                      slot="append"
                    >
                      <div
                        class="iconfont icon-youxiang1"
                        style="margin-top: -10px"
                      ></div>
                    </iv-button>
                  </iv-input>
                </iv-formItem>
              </div>
              <div
                class="ivu-form-item ivu-form-item-required ivu-form-item-error"
              >
                <label class="ivu-form-item-label">密码</label>
                <iv-formItem prop="password">
                  <iv-input
                    autocomplete="off"
                    type="password"
                    v-model="form.password"
                    placeholder="请输入密码"
                  />
                </iv-formItem>
              </div>
            </iv-form>
            <div class="dev-sign-main-aside">
              <iv-button type="success"
                class="ivu-btn ivu-btn-success ivu-btn-long ivu-btn-large"
                @click="regist('registForm')">
                <i class="ivu-icon ivu-icon-md-log-in"></i>
                <span>注册</span>
              </iv-button>
              <span class="ivu-input-prefix">
                <i class="ivu-icon ivu-icon-ios-mail-outline"></i
              ></span>
              <div class="dev-sign-main-aside-tip">
                <!--<p><a href="/recover" class="">忘记密码？</a></p>-->
                <p>
                  已有有账户？
                  <router-link to="/login" class="">登录</router-link>
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
import { mixin } from "@/utils";

export default {
  name: "registForm",
  data() {
    return {
      form: {
        identity: "",
        userEmail: "",
        password: "",
        username: "",
        nickname: "",
      },
      accountIdentity: [
        {
          value: "STUDENT",
          label: "学生",
        },
        {
          value: "WORKER",
          label: "工作者",
        },
        {
          value: "TEACHER",
          label: "教育者",
        },
      ],
      registerRules: {
        identity: [
          { required: true, message: "身份不能为空", trigger: "blur" },
        ],
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
        ],
        nickname: [
          { required: true, message: "用户真实名不能为空", trigger: "blur" },
        ],
        password: [
          { required: true, message: "用户密码不能为空且长度至少为6位", trigger: "blur"},
        ],
        email: [
          { required: true, message: "请填写邮箱", trigger: "blur" },
          {
            type: "string",
            message: "邮箱格式不正确",
            trigger: "blur",
            transform(value) {
              if (
                !/^\w+((-\w+)|(\.\w+))*@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(
                  value
                )
              ) {
                return true;
              } else {
              }
            },
          },
          {
            type: "string",
            message: "长度不能超过30位",
            trigger: "blur",
            max: 30,
          },
        ],
      },
    };
  },
  mixins: [mixin],
  created() {},
  computed: {},
  methods: {
    regist(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let params = {
            accountIdentity: this.form.identity,
            userEmail: this.form.userEmail,
            userPassword: this.form.password,
            userNickname: this.form.nickname,
            userName: this.form.username,
          };
          this.$axios.post("/user/signin", params)
          .then(({ data }) => {
            if (data && data.code === 1) {
              this.$Message.success("注册成功，请前往注册所填写的邮箱进行账号激活！");
            } else {
              this.$Message.error(data.msg);
            }
          });
        }else{
          this.$Message.error('参数校验不合法')
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
