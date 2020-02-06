 
<template>
  <div>
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.0em;"
        slot="text"
        v-resize-text
      >임시 비밀번호 발급</div>
    </ImgBanner>
    
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-container>
      <v-text-field
          label="가입한 이메일을 입력해주세요"
          v-model="email"
          type="text"
          :rules="emailRules"
        ></v-text-field>
      <v-btn :disabled="!valid" color="success" class="mr-4" @click="validate">비밀번호 체크</v-btn>
      </v-container>
    </v-form>

    <!-- <UserInfo v-if="isPwdRight" :isPwdRight="isPwdRight" :userDetail="userDetail"></UserInfo> -->
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import http from "../http-common";

export default {
  name: "FindPwdPage",
  components: {
    ImgBanner,
  },

  computed: {
    requestHeader: function() {
      return this.$store.getters.requestHeader
    }
  },

  data() {
    return {
      valid : false,
      emailCheck : true,

      email : "",

      rules: {
        required: value => !!value || "Required.",
        min_8: v => v.length >= 8 || "Min 8 characters",
        min_4: v => v.length >= 4 || "Min 8 characters",
        is_num: v => !isNaN(v) || "Please input number",
        emailMatch: () => "The email and password you entered don't match"
      },
      emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+\..+/.test(v) || "E-mail must be valid"
    ]
    }
  },
  methods: {
    
    validate() {
      if (this.$refs.form.validate()) {
      
        http
          .post('/findPwd',{
            email: this.email,
            name: localStorage.getItem('name')
          })
          .then(res => {
            console.log(res)
            // if (res.data.state == 'succ') {
            //   this.$router.push('/userinfo')
            // } else {
            //   alert('비밀번호 오류입니다.')
            // }
          })
          .catch(err => {
            // alert('비밀번호 오류입니다.')
            console.log(err)
          })
          this.pwdCheck = false
      }
    }
  },

  
}

</script>
<style scoped>

</style>