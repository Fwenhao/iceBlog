<template>
  <el-pagination
    :current-page="pageInfo.current"
    :page-size="pageInfo.size"
    :total="pageInfo.total"
    :page-sizes="[10, 20, 30, 40]"
    layout="total, sizes, prev, pager, next, jumper"
    style="margin: 0px"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  >
  </el-pagination>
</template> 

<script>
export default {
  props: {
    current: {
      default: 0,
    },
    size: {
      default: 0,
    },
    total: {
      default: 0,
    },
    pageInfo: {
      type: Object,
      default() {
        return {
          current: 0,
          size: 10,
          total: 0,
        };
      },
      validator(val) {
        var requiredKeys = ["current", "total"];
        for (let k in requiredKeys) {
          if (typeof val[requiredKeys[k]] == "undefined") {
            console.error("分页组件pageInfo参数值中缺少" + requiredKeys[k]);
            return false;
          }
        }
        return true;
      },
    },
  },
  methods: {
    handleSizeChange(val) {
      this.$emit("change", {
        current: this.pageInfo.current,
        size: val,
        total: this.pageInfo.total,
      });
    },
    handleCurrentChange(val) {
      this.$emit("change", {
        current: val,
        size: this.pageInfo.size,
        total: this.pageInfo.total,
      });
    },
  },
};
</script>
