<template>
  <d2-container>
    <template slot="header">
      <div class="header">
        <div class="left"></div>
        <div class="right">
          <el-input
            v-model.trim="pageInfo.name"
            placeholder="状态查询：1:登录 2:注册 3:更新"
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
      <el-table-column label="状态" prop="remark" width="180" align="center">
      </el-table-column>
      <el-table-column
        label="操作时间"
        prop="createTime"
        width="180"
        align="center"
        :formatter="formatDate"
      >
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
        status: this.pageInfo.name,
        pageSize: this.pageInfo.size,
        current: this.pageInfo.current,
      };
      this.$api.getAccountHistory(params).then((res) => {
        if (res) {
          this.tableData = res.records;
          this.total = res.total;
          
           this.$notify({
            title: "提示",
            message: "账号状态记录查询成功",
            type: "success",
          });
        }
      });
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
      this.$api.getAccountHistory(params).then((res) => {
        if (res) {
          this.tableData = res.records;
          this.total = res.total;
          this.$notify({
            title: "提示",
            message: "刷新重置成功",
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
