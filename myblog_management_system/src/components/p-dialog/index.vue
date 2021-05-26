<template>
  <el-dialog
    :visible.sync="visible"
    :width="width"
    :center="true"
    :before-close="handleClose"
    :fullscreen="fullscreen"
    :close-on-press-escape="false"
    :close-on-click-modal="false"
    class="p-dialog"
    :append-to-body="isToBody"
  >

   <el-card slot="title" >
     <h2 align="center">{{title}}</h2>
   </el-card>
    <el-scrollbar :style="{ height: fullscreen ? '100%' : height }">
      <slot >这是弹框内容区域</slot>
    </el-scrollbar>

    <div slot="footer" class="dialog-footer">
      <el-card >
      <slot name="dialog-footer">
        <el-button type="primary" size="medium"  @click="dialogVisible = false">
          确 定
        </el-button>
      </slot>
      </el-card>
    </div>
  </el-dialog>
</template>
<script>
export default {
  props: {
    // 是否显示
    visible: {
      type: Boolean,
      default: false,
    },
    // 标题
    title: {
      type: String,
      default: '提示',
    },
    // 弹框宽度
    width: {
      type: String,
      default: '',
    },
    // 内容高度
    height: {
      type: String,
      default: '300px',
    },
    // 是否全屏
    fullscreen: {
      type: Boolean,
      default: false,
    },
    isToBody: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {

    }
  },
  methods: {
    handleClose() {
      this.$emit('closeDialog')
    },
  },
}
</script>
<style  lang="scss">
  *{
    padding:  0;
    margin:0;
  }
.p-dialog {
  .el-dialog {
    display: flex;
    flex-direction: column;
  }
  .el-dialog__header {
    flex: 0;
    padding:0px;
    .el-dialog__headerbtn {
      top: 15px;
    }
  }
  .el-dialog__body {
    flex: 1;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    padding: 20px;
    .el-scrollbar {
      flex: 1;
    }
    .el-scrollbar__wrap {
      overflow: auto;
      /*padding: 15px 10px;*/
    }
  }
  .el-dialog__footer {
    flex: 0;
    padding: 0px;
  }

}
</style>
