<template>
  <d2-container type="full" class="page">
    <d2-grid-layout v-bind="layout" @layout-updated="layoutUpdatedHandler">
      <d2-grid-item
        v-for="(item, index) in layout.layout"
        :key="index"
        v-bind="item"
        @resize="resizeHandler"
        @move="moveHandler"
        @resized="resizedHandler"
        @moved="movedHandler"
      >
        <el-card shadow="never" class="page_card">
          <el-tag
            v-if="item.i === '0'"
            size="big"
            type="success"
            slot="header"
            shadow="hover"
            >个人信息</el-tag
          >
          <template v-if="item.i === '0'">
            <div class="d2-mb">用户名: {{ userMessageInfo.userName }}</div>
            <div class="d2-mb">真实名:{{ userMessageInfo.nickName }}</div>
            <div class="d2-mb">邮箱:{{ userMessageInfo.userEmail }}</div>
            <div class="d2-mb">
              用户身份:{{ userMessageInfo.accountIdentity }}
            </div>
            <div class="d2-mb">性别:{{ userMessageInfo.userSex }}</div>

            <router-link to="/userInfo"
              ><el-button type="success" style="width: 50%">
                编辑
              </el-button></router-link
            >
          </template>

          <el-tag
            v-if="item.i === '1'"
            size="big"
            type="warning"
            slot="header"
            shadow="hover"
            >发现自然</el-tag
          >
          <template v-if="item.i === '1'">
            <el-row type="flex" :gutter="20" justify="center">
              <el-col :span="4">
                <router-link to="/articleInfo">
                  <el-button type="primary" icon="el-icon-search"
                    >原创文章
                    <h1 style="padding: 10px">
                      {{ userMessageInfo.articleCount }}
                    </h1></el-button
                  >
                </router-link>
              </el-col>
              <el-col :span="4">
                <router-link to="/articleCollect">
                  <el-button type="primary" icon="el-icon-star-on"
                    >我的收藏
                    <h1 style="padding: 10px">
                      {{ userMessageInfo.collectCount }}
                    </h1></el-button
                  >
                </router-link>
              </el-col>
              <el-col :span="4">
                <el-button type="primary" icon="el-icon-goblet"
                  >我的总访问量
                  <h1 style="padding: 10px">
                    {{ userMessageInfo.vCount }}
                  </h1></el-button
                >
              </el-col>
            </el-row>
          </template>

          <el-tag v-if="item.i === '2'" size="big" slot="header" shadow="hover"
            >发现自然</el-tag
          >
          <el-tag
            v-if="item.i === '2'"
            size="big"
            type="success"
            slot="header"
            shadow="hover"
            >轮播图</el-tag
          >
          <template v-if="item.i === '2'">
            <el-carousel :interval="4000" type="card" height="300px">
              <el-carousel-item v-for="item in imgs" :key="item.id">
                <img :src="item.imgUrl" style="height: 100%; width: 100%" />
              </el-carousel-item>
            </el-carousel>
          </template>

          <el-tag
            v-if="item.i === '3'"
            size="big"
            type="danger"
            slot="header"
            shadow="hover"
            >轮播图</el-tag
          >
          <template v-if="item.i === '3'">
            <el-calendar v-model="value"></el-calendar>
          </template>
        </el-card>
      </d2-grid-item>
    </d2-grid-layout>
  </d2-container>
</template>

<script>
import Vue from "vue";
import { GridLayout, GridItem } from "vue-grid-layout";
Vue.component("d2-grid-layout", GridLayout);
Vue.component("d2-grid-item", GridItem);
export default {
  data() {
    return {
      value: new Date(),
      userMessageInfo: {
        userSex: "",
        nickName: "",
        accountIdentity: "",
        userEmail: "",
        userName: "",
        articleCount: "",
        collectCount: "",
        vCount: "",
      },
      imgs: [
        {
          id: 1,
          imgUrl: "http://img.fyyice.cn/风景1.png",
        },
        {
          id: 2,
          imgUrl: "http://img.fyyice.cn/风景2.jpg",
        },
        {
          id: 3,
          imgUrl: "http://img.fyyice.cn/风景3.jpg",
        },
      ],
      layout: {
        layout: [
          { x: 0, y: 11, w: 3, h: 11, i: "0" },
          { x: 3, y: 11, w: 9, h: 5, i: "1" },
          { x: 0, y: 0, w: 12, h: 11, i: "2" },
          { x: 3, y: 16, w: 9, h: 18, i: "3" },
        ],
        colNum: 12,
        rowHeight: 30,
        isDraggable: true,
        isResizable: true,
        isMirrored: false,
        verticalCompact: true,
        margin: [10, 10],
        useCssTransforms: true,
      },
    };
  },
  mounted() {
    // 加载完成后显示提示
    this.showInfo();
    this.getInfo();
  },
  methods: {
    log(arg1 = "log", ...logs) {
      if (logs.length === 0) {
        console.log(arg1);
      } else {
        console.group(arg1);
        logs.forEach((e) => {
          console.log(e);
        });
        console.groupEnd();
      }
    },
    // 显示提示
    showInfo() {
      this.$notify({
        title: "提示",
        message:
          "你可以按住卡片拖拽改变位置；或者在每个卡片的右下角拖动，调整卡片大小",
      });
    },
    // 测试代码
    layoutUpdatedHandler(newLayout) {
      console.group("layoutUpdatedHandler");
      newLayout.forEach((e) => {
        console.log(
          `{'x': ${e.x}, 'y': ${e.y}, 'w': ${e.w}, 'h': ${e.h}, 'i': '${e.i}'},`
        );
      });
      console.groupEnd();
    },
    resizeHandler(i, newH, newW) {
      this.log("resizeHandler", `i: ${i}, newH: ${newH}, newW: ${newW}`);
    },
    moveHandler(i, newX, newY) {
      this.log("moveHandler", `i: ${i}, newX: ${newX}, newY: ${newY}`);
    },
    resizedHandler(i, newH, newW, newHPx, newWPx) {
      this.log(
        "resizedHandler",
        `i: ${i}, newH: ${newH}, newW: ${newW}, newHPx: ${newHPx}, newWPx: ${newWPx}`
      );
    },
    movedHandler(i, newX, newY) {
      this.log("movedHandler", `i: ${i}, newX: ${newX}, newY: ${newY}`);
    },
    getInfo() {
      this.$api.getUserInfo().then((res) => {
        if (res) {
          this.userMessageInfo.userSex = res.userSex;
          this.userMessageInfo.nickName = res.nickName;
          this.userMessageInfo.accountIdentity = res.accountIdentity;
          this.userMessageInfo.userEmail = res.userEmail;
          this.userMessageInfo.userName = res.userName;
          this.userMessageInfo.articleCount = res.articleCount;
          this.userMessageInfo.collectCount = res.collectCount;
          this.userMessageInfo.vCount = res.vCount;
          console.log(this.userMessageInfo.userSex);
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.page {
  .vue-grid-layout {
    background-color: $color-bg;
    border-radius: 4px;
    margin: -10px;
    .page_card {
      height: 100%;
      @extend %unable-select;
    }
    .vue-resizable-handle {
      opacity: 0.3;
      &:hover {
        opacity: 1;
      }
    }
  }
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>