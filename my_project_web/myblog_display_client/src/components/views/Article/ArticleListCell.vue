<template>
  <div>
    <a>
      <iv-card class="ivu">
        <div class="ivu-row">
          <router-link
            :to="{ name: 'article', params: { articleId: article.keyId } }"
          >
            <div class="ivu-col ivu-col-span-7">
              <img
                class="live-cover"
                src="http://img.fyyice.cn/%E5%A4%8D%E4%BB%87%E8%80%85%E8%81%94%E7%9B%9F-%E9%92%A2%E9%93%81%E4%BE%A0.png"
                style="width: 100%"
              />
            </div>
            <div class="ivu-col ivu-col-span-17">
              <div class="live-info">
                <div class="live-title">{{ article.title }}</div>
                <div class="live-desc">{{ article.description }}</div>
              </div>
              <div class="live-down">
                <div class="live-down-left">
                  <!-- 标签tags -->
                  <div
                    class="live-for"
                    v-for="(item, index) in article.tags"
                    :key="item"
                    :name="item"
                  >
                    <iv-icon type="ios-pricetag" :color="index | mapTagColor" />
                    {{ item }}
                  </div>
                </div>
                <div class="live-down-right">
                  <div class="live-name">
                    <iv-icon type="ios-contact" class="icon" />{{
                      article.author == null ? "管理员" : article.author
                    }}
                  </div>
                  <div class="live-time">
                    <iv-icon type="ios-timer-outline" class="icon" />
                    {{ article.createTime | formatDate }}
                  </div>
                </div>
              </div>
            </div>
          </router-link>
        </div>
      </iv-card>
    </a>
  </div>
</template>

<script type="text/ecmascript-6">
import { mixin } from "@/utils";
import {dateFilter} from '../../../utils/date'

export default {
  filters: {
        formatDate(time) {
            var date = new Date(time);
            return dateFilter(date);
        }
  },
  props: {
    article: {
      Type: Object,
    },
    type: "",
  },
  mixins: [mixin],
  computed: {
    themeClass: function () {
      if (this.article.coverType === 1) {
        return "big-image";
      } else {
        return "";
      }
    },
  },
};
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
@import '../../../common/stylus/index.styl';

> a {
  display: block;
  cursor: default;
  border: 1px solid $color-border;

  &:hover {
    border: 1px solid $color-border-hover;
    box-shadow: 2px 2px 3px $color-border;
  }

  .text-wrapper {
    padding: 20px 20px 0 20px;
    text-align: left;

    @media only screen and (max-width: 768px) {
      padding: 15px 15px 0 15px;
    }

    .title {
      font-size: 23px;
      font-weight: 100;
      line-height: 27px;

      span.special {
        border-radius: $border-radius;
        font-size: 12px;
        font-weight: 100;
        padding: 3px 5px;
        margin-left: 1px;
        vertical-align: top;
        color: $default-background-color;
        background: $iview-secondary-warning-color;
        cursor: pointer;
      }

      a {
        color: $color-typegraphy-title;
        cursor: pointer;

        &:hover {
          color: $color-typegraphy-title-hover;
          text-decoration: underline;
        }
      }
    }

    .info {
      margin-top: 10px;
      font-size: 14px;
      line-height: 18px;
      font-weight: 200;

      a {
        color: #777;
        cursor: pointer;

        &:hover {
          color: $color-main-primary;
          text-decoration: underline;
        }
      }

      .publish-time {
        margin-left: 20px;
      }
    }

    .line {
      width: 50px;
      margin-top: 30px;
      border-1px(rgba(7, 17, 27, 0.4));

      &::after {
        margin-bottom: 15px;
      }
    }

    .tags {
      /* cursor: pointer; */
      margin: 8px 0;
    }

    .desc {
      color: #666;
      font-size: 14px;
      line-height: 20px;
      font-weight: 200;

      a {
        color: $color-main-primary;
        font-weight: 500;
        cursor: pointer;

        &:hover {
          text-decoration: underline;
        }
      }
    }

    .operate_info {
      font-size: 14px;
      margin: 15px 0 20px;

      span {
        margin-right: 8px;

        + span {
          margin-left: 8px;
        }

        a {
          cursor: default;

          &:hover {
            color: $color-main-primary;
          }
        }

        img {
          width: 100%;
        }
      }
    }
  }
}

.before {
  box-sizing: border-box;
}

.ivu {
  background-color: #fff;
  margin-bottom: 10px;
  cursor: pointer;
}

.live-cover {
  height: 150px;
  background-repeat: no-repeat;
  background-size: cover;
  background-position: 50%;
  border-radius: 4px;
}

.live-info {
  height: 110px;
  padding: 10px;
  overflow: hidden;
}

.live-title {
  font-size: 16px;
  font-weight: 700;
}

.live-desc {
  color: #9ea7b4;
  margin-top: 20px;
  line-height: 20px;
}

.live-down-left {
  margin-top: 20px;
  float: left;
  width: 200px;
}

.live-for {
  float: left;
  margin-left: 15px;
}

.live-down-right {
  width: 50px;
  margin-left: 400px;
}

.live-name {
  width: 200px;
  font-size: 15px;
}

.live-time {
  color: #9ea7b4;
  width: 200px;
  margin-top: 5px;
  font-size: 10px;
  line-height: 20px;
}

.icon {
  margin-right: 10px;
}

@media (max-width: 768px) {
  .live-cover {
    height: 100px;
  }

  .live-info {
    height: 100px;
  }

  .live-down {
    display: none;
  }
}
</style>
