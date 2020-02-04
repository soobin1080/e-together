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

    <v-form ref="form" v-model="valid" lazy-validation>
      <v-container>
      <v-text-field
          v-model="user.pwd"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          type="password"
        ></v-text-field>
      <v-btn :disabled="!valid" color="success" class="mr-4" @click="validate">비밀번호 체크</v-btn>
      </v-container>
    </v-form> 
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import http from "../http-common";
export default {
  name: "PwdCheckPage",
  components: {
    ImgBanner
  },

  computed: {
    requestHeader: function() {
      return this.$store.getters.requestHeader
    }
  },

  data() {
    return {
      user: {
        email: this.$store.state.user,
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
    validate() {
      if (this.$refs.form.validate()) {
        // console.log(this.requestHeader)
        // console.log(this.credentials.email)
        // console.log(this.credentials.pwd)
        let form = new FormData()
        form.append('email', this.email)
        form.append('pwd', this.pwd)
        http
          .post('/pwdCheck',{
            email: this.user.email,
            pwd : this.user.pwd,
            // Authorization: 'bearer ' + this.$store.state.accessToken
          }, this.$store.getters.requestHeader)
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.log(err)
          })
      }
    }
  },

  
}

</script>
<style scoped>

</style>