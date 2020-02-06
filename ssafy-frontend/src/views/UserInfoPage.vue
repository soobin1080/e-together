<template>
  <div>
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.5em;"
        slot="text"
        v-resize-text
      >User Info</div>
    </ImgBanner>

    <v-form ref="form" v-model="valid" lazy-validation>
      <v-container fluid style="width:700px; padding-bottom:80px">
        <h2 class="text-center mt-5">회원 정보</h2>

        <v-text-field
          v-model="user.name"
          label="name"
          readonly
        ></v-text-field>

        <v-text-field 
          v-model="user.email"
          label="E-mail" 
          readonly
        ></v-text-field>


        <v-text-field
          v-model="user.phone"
          label="Phone"
          readonly
        ></v-text-field>
       
        <v-btn
          v-if="isLogin"
          color="success"
          class="mr-4"
          to="/userinfomodify"
          style="float:right"
        >
        수정하기
        </v-btn>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import ResizeText from "vue-resize-text";
import http from "../http-common";
export default {
  name: "UserInfoPage",
  components: {
    ImgBanner
  },
  directives: {
    ResizeText
  },
  data: () => ({
    show1: false,
    valid: true,
    isLogin: false,
    user: {
      name: "",
      email: "",
      phone: "",
    },
    rules: {
      required: value => !!value || "Required.",
      min_8: v => v.length >= 8 || "Min 8 characters",
      min_4: v => v.length >= 4 || "Min 8 characters",
      is_num: v => !isNaN(v) || "Please input number",
      emailMatch: () => "The email and password you entered don't match"
    },
    nameRules: [
      v => !!v || "Name is required",
      v => (v && v.length <= 10) || "Name must be less than 10 characters"
    ],
    emailRules: [
      v => !!v || "E-mail is required",
      v => /.+@.+\..+/.test(v) || "E-mail must be valid"
    ],
    phoneNumbers: ["010", "011"]
  }),
  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    getUserInfo(UserEmail) {
      let myemail = localStorage.getItem('email')
      console.log(this.$store.getters.isLoggedIn)
      http
        .post(`/myselfDetail/${myemail}`, this.$store.getters.requestHeader)
          .then(res => {
            console.log(res)
            if (this.$store.getters.isLoggedIn) {
              this.user.email = res.data.email
              this.user.name = res.data.name
              this.user.phone = res.data.phone
              this.isLogin = this.$store.getters.isLoggedIn
            }
          })
          .catch(err => {
            consloe.log(err)
          })
      // this.user.name = localStorage.getItem('user')
      // this.user.email = localStorage.getItem('email')
      // this.user.phone = localStorage.getItem('phone')
    }
  },

  mounted() {
    this.getUserInfo();
  }
};
</script>