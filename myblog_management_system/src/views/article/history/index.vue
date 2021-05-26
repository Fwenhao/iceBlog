<template>
  <d2-container>
    <template slot="header">
      <div class="header">
        <div class="left"></div>
        <div class="right">
          <el-input
            v-model.trim="pageInfo.name"
            placeholder="请输入文章标题"
            class="input-w300"
            @keyup.enter.native="init()"
          />
        </div>
        <el-button type="primary" style="margin-left: 5px" @click="init()">
          <i class="fa fa-plus" aria-hidden="true"></i>
          查询
        </el-button>
      </div>
    </template>
    <el-card>
      <el-form
        :inline="true"
        :model="pageInfo"
        ref="searchForm"
        size="small"
        style="margin-bottom: -18px"
        label-width="80px"
      >
        <el-form-item>
          <el-button @click="refresh">
            <d2-icon name="refresh" class="d2-mr-5" />
            重置
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card> </el-card>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column
        label="文章id"
        prop="articleId"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column label="标题" prop="title" width="180" align="center">
      </el-table-column>
      <el-table-column
        label="浏览时间"
        prop="createTime"
        width="180"
        align="center"
        :formatter="formatDate"
      >
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleQuery(scope.row)">
            详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div slot="footer">
      <div class="foot">
        <fwh-pagination
          :page-info="{ ...pageInfo, total }"
          @change="changePage"
        ></fwh-pagination>
      </div>
    </div>
    <!-- 弹窗位置 -->
    <fwh-dialog
      :title="title"
      :visible="visible"
      @closeDialog="closeDialog"
      :height="dialogHeight"
      :width="dialogWidth"
    >
      <div class="content">这是弹窗的主要内容根据实际写</div>
    </fwh-dialog>
  </d2-container>
</template>

<script>
import { dateFilter } from "../../../date";
export default {
  components: {},

  data() {
    return {
      //分页数据
      pageInfo: {
        current: 1,
        size: 10,
        name: "",
      },
      total: 0, //页码总数
      title: "",
      visible: false,
      dialogHeight: "",
      dialogWidth: "",
      //table数据
      tableData: [],
    };
  },
  components: {},
  computed: {},
  mounted() {
    this.init();
  },
  methods: {
    changePage(val) {
      this.pageInfo = {
        ...val,
      };
      this.init();
    },
    init() {
      let params = {
        name: this.pageInfo.name,
        pageSize: this.pageInfo.size,
        current: this.pageInfo.current,
      };
      this.$api.getBrowserRecord(params).then((res) => {
        if (res) {
          console.log(res)
          this.tableData = res.records;
          this.total = res.total;
          
           this.$notify({
            title: "提示",
            message: "浏览记录查询成功",
            type: "success",
          });
        }
      });
    },
    //编辑函数----row就是点击的这行数据
    handleEdit(row) {
      this.title = "修改板块";
      this.visible = true;
      this.dialogHeight = "200px"; //动态设置弹窗高
      this.dialogHeight = "200px"; //动态设置弹窗宽
    },
    //查看函数
    handleQuery(row) {
      window.open("http://localhost:8002/article/" + row.articleId);
    },
    //删除函数
    handleDelete(row) {
      this.title = "删除板块";
      this.visible = true;
    },
    //关闭弹窗的回调函数
    closeDialog() {
      this.visible = false;
    },
    formatDate(row) {
      var date = new Date(row.createTime);
      return dateFilter(date);
    },
    refresh() {
      let params = {
        name: "",
        pageSize: this.pageInfo.size,
        current: this.pageInfo.current,
      };
      this.$api.getBrowserRecord(params).then((res) => {
        if (res) {
          this.tableData = res.records;
          this.total = res.total;
          this.$notify({
            title: "提示",
            message: "刷新成功",
            type: "success",
          });
        }
      });
    },
  },
};
</script>
<style lang="scss">
* {
  padding: 0;
  margin: 0;
}
.header {
  display: flex;
  .left {
    width: 50%;
  }
  .right {
    width: 50%;
  }
}
.foot {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
