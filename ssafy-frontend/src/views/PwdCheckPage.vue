<template>
  <div>
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.5em;"
        slot="text"
        v-resize-text
      >Pwd Check</div>
    </ImgBanner>
    
    <v-form ref="form" v-if="pwdCheck" v-model="valid" lazy-validation>
      <v-container>
      <v-text-field
          v-model="user.pwd"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          type="password"
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
import UserInfo from "../components/UserInfo";
export default {
  name: "PwdCheckPage",
  components: {
    ImgBanner,
    UserInfo
  },

  computed: {
    requestHeader: function() {
      return this.$store.getters.requestHeader
    }
  },

  data() {
    return {
      pwdCheck : true,
      isPwdRight: false,
      user: {
        email: localStorage.getItem('email'),
        pwd: "",
        //Authorization : 'Bearer '+this.$store.state.accessToken
      },

      show1: false,
      valid: true,
      rules: {
        required: value => !!value || "Required.",
        min_8: v => v.length >= 8 || "Min 8 characters",
        min_4: v => v.length >= 4 || "Min 8 characters",
        is_num: v => !isNaN(v) || "Please input number",
        emailMatch: () => "The email and password you entered don't match"
      },
    }
  },
  methods: {
    // getUserDetail() {
    //   console.log('getUserDetail active')
    //   let myEmail = this.$store.state.email
    //   http
    //     .post(`/myselfDetail/${myEmail}`)
    //       .then(res => {
    //         // console.log(res)
    //         this.userDetail.email = res.data.email
    //         this.userDetail.name = res.data.name
    //         this.userDetail.phone = res.data.phone
    //       })
    //       .catch(err => {
    //         console.log(err)
    //       })

    // },
    validate() {
      if (this.$refs.form.validate()) {
        // console.log(this.requestHeader)
        // console.log(this.credentials.email)
        // console.log(this.credentials.pwd)
        
        http
          .post('/pwdCheck',{
            email: this.user.email,
            pwd : this.user.pwd,
            // Authorization: 'bearer ' + this.$store.state.accessToken
          }, this.$store.getters.requestHeader)
          .then(res => {
            console.log(res)
            if (res.data.state == 'succ' && this.$store.getters.isLoggedIn == true) {
              this.$router.push('/userinfo')
            } else {
              alert('비밀번호 오류입니다.')
            }
          })
          .catch(err => {
            alert('비밀번호 오류입니다.')
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