<template>
  <d2-container>
    <template slot="header">
      <div class="header">
        <div class="left"></div>
        <div class="right">
          <el-input
            v-model.trim="pageInfo.name"
            placeholder="请输入标题相关内容"
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
       <el-table-column label="收藏文章id" prop="collectArticleId" width="180" align="center">
      </el-table-column>
      <el-table-column label="标题" prop="title" width="180" align="center">
      </el-table-column>
     
      <el-table-column
        label="评论时间"
        prop="createTime"
        width="180"
        align="center"
        :formatter="formatDate"
      >
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="info" @click="handleQuery(scope.row)">
            详情
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">
            删除
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
        content: this.pageInfo.name,
        pageSize: this.pageInfo.size,
        current: this.pageInfo.current,
      };
      console.log("params", params);
      this.$api.getCollect(params).then((res) => {
        if (res) {
          console.log(res);
          this.tableData = res.records;
          this.total = res.total;
          this.$notify({
            title: "提示",
            message: "成功返回收藏记录",
            type: "success",
          });
        }
      });
    },

    //编辑函数----row就是点击的这行数据

    //查看函数
    handleQuery(row) {
      console.log(row)
      window.open("http://localhost:8002/article/" + row.collectArticleId);
    },
    //删除函数
    handleDelete(row) {
      this.$confirm("此操作将永久删除该收藏记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then((action) => {
          if (action === "confirm") {
            let param = {
              collectId : row.keyId
            }
            this.$api.deleteCollect(param).then(() => {
                this.$message({
                  type: "success",
                  message: "删除成功!",
                });
                this.init()
            });
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    formatDate(row) {
      var date = new Date(row.createTime);
      return dateFilter(date);
    },
    refresh() {
      let params = {
        content: "",
        pageSize: this.pageInfo.size,
        current: this.pageInfo.current,
      };
      this.$api.getCollect(params).then((res) => {
        if (res) {
          this.tableData = res.records;
          this.total = res.total;
          this.$notify({
            title: "提示",
            message: "表格数据已初始化",
            type: "success",
          });
          this.pageInfo.name= "";
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
