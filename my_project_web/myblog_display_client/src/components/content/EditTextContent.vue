<template>
  <div class="bookNote-list-content">
    <iv-row>
      <iv-col :xs="24" :sm="24" :md="24" :lg="17">
        <!--<div id="edtiText" style="margin-top: 30px">
          <textarea id="editor1" rows="10" cols="80" ></textarea>
        </div>-->
        <iv-form ref="article" relus="articleRelus" :model="article" :label-width="0">
          <!-- <iv-input type="hidden" name="id" v-model="article.id" /> -->
          <!-- <iv-formItem label="选择封面：">
            <iv-upload
              action=""
              :format="['jpg', 'jpeg', 'png', 'gif']"
              :on-format-error="handleFormatError"
              :on-exceeded-size="handleMaxSize"
              :before-upload="handleBeforeUpload"
            >
              <iv-button icon="ios-cloud-upload-outline">上传封面</iv-button>
              <span v-if="article.articleHeadPic !== ''">上传成功</span>
            </iv-upload>
          </iv-formItem> -->

          <iv-formItem label="文章标题：">
            <iv-input
              placeholder="请填写文章标题..."
              :maxlength="50"
              v-model="article.title"
              style="width: 300px"
              type="text"
            ></iv-input>
          </iv-formItem>

          <iv-formItem label="文章概要：">
            <iv-input
              type="textarea"
              placeholder="简单描述一下该文章内容吧..."
              v-model="article.description"
              :maxlength="100"
              style="width: 500px"
            ></iv-input>
          </iv-formItem>
          
          <iv-formItem label="文章标签：">
            <iv-tag
              v-for="item in countTags"
              :key="item"
              :name="item"
              closable
              @on-close="handleClose"
              >{{ item }}</iv-tag
            >
            <iv-input
              type="text"
              class="input-new-tag"
              v-if="inputVisible"
              v-model="inputValue"
              ref="saveTagInput"
              size="small"
              @keyup.enter.native="handleInputConfirm"
              @blur="handleInputConfirm"
            >
            </iv-input>
            <iv-button
              v-else
              size="small"
              type="dashed"
              icon="ios-add"
              @click="showInput"
              >+ 添加标签</iv-button
            >
          </iv-formItem>

          <iv-formItem label="文章内容：">
            <mavon-editor
              ref="md"
              @imgAdd="imgAdd"
              v-model="article.content"
              :toolbars="toolbars"
              @change="mavonChangeHandle"
            />
          </iv-formItem>

          <iv-formItem label="文章类型：">
            <iv-select style="width:200px" v-model="article.status">
              <iv-option
                v-for="item in statusArr"
                :value="item.key"
                :key="item.key"
                >{{ item.label }}
              </iv-option>
            </iv-select>
          </iv-formItem>

          <!-- <iv-formItem label="选择附件：">
            <iv-upload
              action=""
              :format = "['zip']"
              :on-format-error="handleFormatError"
              :on-exceeded-size="handleMaxSize"
              :before-upload="handleUpload">
              <iv-button icon="ios-cloud-upload-outline">选择附件</iv-button>
              <span v-if = "this.article.enclosure !=='' || this.article.enclosure != null  ">上传成功</span>
            </iv-upload>
          </iv-formItem> -->
        </iv-form>
        <iv-button type="info" @click="saveArticle()">重新发表</iv-button>
      </iv-col>
      <iv-col :xs="0" :sm="0" :md="0" :lg="7">
        <div class="layout-right">
          <recommend></recommend>
          <tag-wall style="margin-top: 15px"></tag-wall>
        </div>
      </iv-col>
    </iv-row>
  </div>
</template>

<script type="text/ecmascript-6">
import Recommend from "@/components/views/Recommend";
import TagWall from "@/components/views/TagWall";
import BrowseMore from "@/components/views/BrowseMore";
// eslint-disable-next-line no-unused-vars
import axios from "axios";
import marked from "marked";

export default {
  data: function () {
    return {
      //tag参数
      countTags: [],
      inputVisible: false,
      inputValue: "",
      content: "",

      statusArr: [
        {
          key: 1,
          label: "原创",
        },
        {
          key: 0,
          label: "转载",
        },
      ],
      article: {
        recommend: false,
        tags: [],
        // articleHeadPic: "",   封面，接口暂未实现
        title: "",
        description: "",
        enclosure: "",
        status: "",
      },
      articleRelus:{
        title:[
           { required: true, message: '标题不能为空', trigger: 'blur' }
        ],
        content:[
           { required: true, message: '内容不能为空', trigger: 'blur' }
        ],
        description:[
           { required: true, message: '概要描述不能为空', trigger: 'blur' }
        ]
      },
      toolbars: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: false, // 标记
        superscript: false, // 上角标
        subscript: false, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: false, // 沉浸式阅读
        htmlcode: true, // 展示html源码
        help: false, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: false, // 下一步
        trash: false, // 清空
        save: false, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: false, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true, // 预览
        boxShadow: false,
      },
    };
  },
  mounted: function () {
    let param = {};
    param.latest = true;
    // 初始化方法
    var articleId = this.$route.query.articleId;

    if (articleId) {
      this.$axios.get('/article/get?articleId='+articleId)
      .then(({ data }) => {
        if (data) {
          this.article = data.data;
          if (data.data.tags.length >= 1) {
            this.countTags = data.data.tags;
          }
        }
      });
    }
  },
  methods: {
    //标签部分
    handleClose(tag) {
      this.countTags.splice(this.countTags.indexOf(tag), 1);
    },

    showInput() {
      this.inputVisible = true;
      this.$nextTick((_) => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.countTags.push(inputValue);
      }
      this.inputVisible = false;
      this.inputValue = "";
    },

    // 上传图片
    imgAdd(pos, file) {
      let formData = new FormData();
      formData.append("file", file);
      axios({
        url: this.$http.adornUrl("/file/upload"),
        method: "post",
        data: formData,
        headers: { "Content-Type": "multipart/form-data" },
      }).then((data) => {
        console.log(JSON.stringify(data.data.result.msg));
        // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
        /**
         * $vm 指为mavonEditor实例，可以通过如下两种方式获取
         * 1.  通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
         * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
         */
        this.$refs.md.$img2Url(pos, data.data.result.msg);
      });
    },

    handleFormatError(file) {
      this.$Notice.warning({
        title: "文件格式不正确",
        desc: "文件 " + file.name + " 格式不正确，请上传格式的图片。",
      });
    },

    handleMaxSize(file) {
      this.$Notice.warning({
        title: "超出文件大小限制",
        desc: "文件 " + file.name + " 太大，不能超过 2M。",
      });
    },

    handleUpload(file) {
      let formData = new FormData();
      formData.append("file", file);
      axios({
        url: this.$http.adornUrl("/file/upload"),
        method: "post",
        data: formData,
        headers: { "Content-Type": "multipart/form-data" },
      }).then((data) => {
        if (data.data.status === 0) {
          this.article.enclosure = data.data.result.msg;
          this.$Message.success("封面上传成功");
        }
      });
    },

    handleBeforeUpload(file) {
      let formData = new FormData();
      formData.append("file", file);
      axios({
        url: this.$http.adornUrl("/file/upload"),
        method: "post",
        data: formData,
        headers: { "Content-Type": "multipart/form-data" },
      }).then((data) => {
        if (data.data.status === 0) {
          this.article.articleHeadPic = data.data.result.msg;
          this.$Message.success("封面上传成功");
        }
      });
    },

    // 保存文章
    saveArticle: function () {
      this.article.tags = this.countTags;
      let article = this.article;
      article.articleContent = this.content;
      console.log("article",article)
      this.$axios.put('/article/update',article)
      .then(({ data }) => {
        if (data && data.code === 1) {
          this.$Message.success("重新编辑并发布成功");
          // 关闭当前标签
          this.$emit("closeCurrentTabs");
          // 跳转到list
          this.$router.push("/article/"+data.data.articleId);
        } else {
          this.$Message.error(data.msg);
          this.$router.push("/login");
        }
      });
    },
    mavonChangeHandle(context, html) {
      this.content = html;
      this.article.articleContent = marked(html);
    },
  },
  components: {
    recommend: Recommend,
    "tag-wall": TagWall,
    "browse-more": BrowseMore,
  },
};
</script>

<style lang="stylus" rel="stylesheet/stylus">
.bookNote-list-content {
  width: auto;

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

.iv-tag + .iv-tag {
  margin-left: 10px;
}

.button-new-tag {
  margin-left: 10px;
  height: 20px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}

.input-new-tag {
  width: 80px;
  margin-left: 0px;
}
</style>
