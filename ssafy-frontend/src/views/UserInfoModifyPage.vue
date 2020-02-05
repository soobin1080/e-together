<template>
  <div>
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.5em;"
        slot="text"
        v-resize-text
      >회원정보 수정</div>
    </ImgBanner>

    <v-form ref="form" v-model="valid" lazy-validation>
      <v-container fluid style="width:700px; padding-bottom:80px">
        <h2 class="text-center mt-5">수정</h2>
        <br />
        <v-text-field
          v-model="computedUser.name"
          :rules="nameRules"
          label="nickname"
          counter="20,"
          required
          autocomplete="name"
        ></v-text-field>

        <v-text-field v-model="computedUser.email" :rules="emailRules" label="E-mail" required></v-text-field>

        <v-text-field
          v-model="computedUser.phone"
          label="phone"
          :rules="[rules.required, rules.is_num]"
          required
          autocomplete="tel"
        ></v-text-field>

        <div class="btn" style="float:right">
          <v-btn color="primary" class="mr-4">비밀번호 수정</v-btn>
          <v-btn color="error" class="mr-4" @click="reset">Reset</v-btn>
          <v-btn :disabled="!valid" color="success" class="mr-4" @click="validate">수정!</v-btn>
        </div>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import ResizeText from "vue-resize-text";
import http from "../http-common";
export default {
  name: "SignUpPage",
  components: {
    ImgBanner
  },
  directives: {
    ResizeText
  },
  data: () => ({
    user : {
      name: "",
      email: "",
      phone: "",
    },

   // nickname: "",
    show1: false,
    valid: true,
    pwd: "",
    rules: {
      required: value => !!value || "Required.",
      min_8: v => v.length >= 8 || "Min 8 characters",
      min_4: v => v.length >= 4 || "Min 8 characters",
      is_num: v => !isNaN(v) || "Please input number",
      emailMatch: () => "The email and password you entered don't match"
    },
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 20) || "Name must be less than 20 characters"
    ],
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+\..+/.test(v) || "E-mail must be valid"
    ]
  }),
  methods: {
    getUserDetail() {
      console.log('getUserDetail active')
      let myEmail = this.$store.state.user
      http
        .post(`/myselfDetail/${myEmail}`)
          .then(res => {
            // console.log(res)
            this.user.email = res.data.email
            this.user.name = res.data.name
            this.user.phone = res.data.phone
          })
          .catch(err => {
            console.log(err)
          })

    },
    validate() {
      if (this.$refs.form.validate()) {
        // let formData = new formData()
        // formData.append('email', this.email)
        // formData.append('pwd', this.pwd)
        // formData.append('phone', this.phone)
        http
          .post('/updateMyself', this.user)
          .then(res => {
            console.log(res)
            // this.$router.push("/")
          })
          .catch (err => {
            console.log(err)
          })
        this.snackbar = true;
      }
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
     reset() {
      this.$refs.form.reset();
    },
  },
  mounted() {
    this.getUserDetail()
  },

  computed: {
    computedUser: function() {
      return this.user
    }
  }
};
</script>
<style scoped>
</style>