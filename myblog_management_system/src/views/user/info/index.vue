<template>
  <d2-container>
    <template slot="header">header info</template>
    <el-form ref="userForm" :model="userForm" :rules="rules">
      <el-form-item
        label="身份"
        :label-width="formLabelWidth"
        prop="accountIdentity"
      >
        <el-select v-model="userForm.accountIdentity" placeholder="" disabled>
          <el-option label="学生" value="STUDENT"></el-option>
          <el-option label="工作者" value="WORKER"></el-option>
          <el-option label="教育行业者" value="TEACHER"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="KeyId" :label-width="formLabelWidth" prop="id">
        <el-col :span="8">
          <el-input v-model="userForm.id" disabled></el-input>
        </el-col>
      </el-form-item>
      <el-form-item
        label="用户名"
        :label-width="formLabelWidth"
        prop="authName"
      >
        <el-col :span="8">
          <el-input v-model="userForm.authName" disabled></el-input>
        </el-col>
      </el-form-item>

      <el-form-item
        label="真实名"
        :label-width="formLabelWidth"
        prop="nickName"
      >
        <el-col :span="8">
          <el-input v-model="userForm.nickName" disabled></el-input>
        </el-col>
      </el-form-item>

      <el-form-item
        label="性别"
        :label-width="formLabelWidth"
        prop="accountIdentity"
      >
        <el-select v-model="userForm.sex" placeholder="性别">
          <el-option label="男" value="MEN"></el-option>
          <el-option label="女" value="WOMEN"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item
        label="邮箱"
        :label-width="formLabelWidth"
        clearable
        prop="email"
      >
        <el-col :span="8">
          <el-input v-model="userForm.email" clearable></el-input>
        </el-col>
      </el-form-item>
      <el-form-item
        label="号码"
        :label-width="formLabelWidth"
        clearable
        prop="phone"
      >
        <el-col :span="8">
          <el-input v-model="userForm.phone" clearable></el-input>
        </el-col>
      </el-form-item>

      <el-form-item
        label="年龄"
        :label-width="formLabelWidth"
        clearable
        prop="age"
      >
        <el-col :span="8">
          <el-input v-model.number="userForm.age" clearable></el-input>
        </el-col>
      </el-form-item>

      <el-form-item
        label="权限身份"
        :label-width="formLabelWidth"
        clearable
        prop="identity"
      >
      
        <el-col :span="8">
          <el-input v-model="userForm.identity" disabled></el-input>
        </el-col>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <div class="left"></div>
      <el-button @click="resetForm('userForm')">重 制</el-button>
      <el-button type="primary" @click="sumbit('userForm')">确 定</el-button>
    </div>
  </d2-container>
</template>

<script>
export default {
  data() {
    return {
      userMessage: "",
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
        nickName: [
          {
            required: true,
            message: "请输入真实名",
            trigger: "blur",
          },
        ],
        authName: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        age: [
          { required: true, message: "年龄不能为空" },
          { type: "number", message: "年龄必须为数字值" },
        ],
        phone: [
          {
            required: true,
            message: "请输入号码",
            trigger: "blur",
          },
          {
            pattern: /^1[3|5|7|8|9]\d{9}$/,
            message: "请输入正确的号码格式",
            trigger: "change",
          },
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
        accountIdentity: [
          {
            required: true,
            message: "请选择你的身份，注册提交后不可更改！",
            trigger: "change",
          },
        ],
      },
      userForm: {
        nickName: "",
        accountIdentity: "",
        authName: "",
        email: "",
        sex: "",
        id: "",
        phone: "",
        identity: "",
        age: null,
      },
      formLabelWidth: "120px",
    };
  },
  mounted() {
    this.getUserInfo();
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$message.info("表单重置成功(不可填属性依旧存在,显示效果问题)");
    },
    getUserInfo() {
      this.userMessage = JSON.parse(sessionStorage.getItem("userMessage"));
      console.log(this.userMessage);
      this.userForm.nickName = this.userMessage.userInfo.nickName;
      this.userForm.accountIdentity = this.userMessage.userInfo.accountIdentity;
      this.userForm.email = this.userMessage.userInfo.userEmail;
      this.userForm.authName = this.userMessage.userInfo.userName;
      this.userForm.sex = this.userMessage.userInfo.userSex;
      this.userForm.id = this.userMessage.userInfo.keyId;
      this.userForm.phone = this.userMessage.userInfo.userPhone;
      this.userForm.identity = this.userMessage.userInfo.userIdentify;
      this.userForm.age = this.userMessage.userInfo.userAge;
    },
    sumbit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let data = {
            userAge: this.userForm.age,
            userSex: this.userForm.sex,
            userPhone: this.userForm.phone,
            userEmail: this.userForm.email,
          };
          this.$api.updateUser(data).then((res) => {
            if (res) {
              console.log("res------", res);
              this.$notify({
                title: "提示",
                message: "用户信息更新成功，重新登陆后刷新",
                type: "success",
              });
            }
          });
        }
      });
    },
  },
};
</script>
