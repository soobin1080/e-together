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
          v-model="credentials.pwd"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min_8]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="비밀번호를 입력해주세요"
          hint="At least 8 characters"
          counter="16"
          @click:append="show1 = !show1"
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

  data: () => ({
    credentials: {
      email: this.$store.state.user,
      pwd: "",
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
    
  }),
  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        console.log(this.$store.state.accessToken)
        http
          .post('/pwdCheck',  this.credentials, {
          
          })
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