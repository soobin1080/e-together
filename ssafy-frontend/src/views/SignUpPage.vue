<template>
  <div>
    <ImgBanner>
      <div
        class="text-center text-white"
        style="line-height:1.2em;font-size:2.5em;"
        slot="text"
        v-resize-text
      >Sign Up</div>
    </ImgBanner>

    <v-form ref="form" v-model="valid" lazy-validation>
      <v-container fluid style="width:700px; padding-bottom:80px">
        <h2 class="text-center mt-5">회원가입</h2>
        <br />
        <v-text-field
          v-model="credentials.name"
          :rules="nameRules"
          label="Name"
          counter="20,"
          required
          autocomplete="name"
        ></v-text-field>

        <div>
          <v-text-field v-model="credentials.email" :rules="emailRules" label="E-mail" required></v-text-field>
          <v-btn @click="emailcheck" small color="blue" style="float:right; color:white">중복 확인</v-btn>
        </div>

        <br />
        <v-text-field
          v-model="credentials.pwd"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min_8]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="Password"
          hint="At least 8 characters"
          counter="16"
          @click:append="show1 = !show1"
        ></v-text-field>

        <v-text-field
          v-model="credentials.phone"
          label="Phone number"
          :rules="[rules.required, rules.is_num, rules.min_11]"
          required
          autocomplete="tel"
        ></v-text-field>

        <div class="btn" style="float:right">
          <v-btn color="error" class="mr-4" @click="reset">Reset</v-btn>
          <v-btn :disabled="!valid" color="success" class="mr-4" @click="validate">가입!</v-btn>
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
    credentials: {
      name: "",
      email: "",
      phone: "",
      pwd: ""
    },
    duplicate: false,
    // nickname: "",
    show1: false,
    valid: true,
    pwd: "",
    rules: {
      required: value => !!value || "Required.",
      min_8: v => v.length >= 8 || "Min 8 characters",
      min_4: v => v.length >= 4 || "Min 4 characters",
      min_11: v => v.length == 11 || " '-'을 제외한 11자리를 입력해주세요!",
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
  watch: {
    credentials:{      
      email(newVal) {
      fetch(`/${newVal}`).then((data) => {
      this.email = data;
      this.duplicate=false;      
    });
    },

  //   movie(movie) {
  //   // Fetch data about the movie
  //   fetch(`/${movie}`).then((data) => {
  //     this.movieData = data;
  //   });
  // }
    deep : true,
    immediate : true
    }
  },
  methods: {
    validate() {
      if(this.duplicate==false){
        alert("E-mail 중복 확인을 해주세요!");
      }
     else if (this.$refs.form.validate()) {
        // let formData = new formData()
        // formData.append('email', this.email)
        // formData.append('pwd', this.pwd)
        // formData.append('phone', this.phone)
        http
          .post("auth/signup", this.credentials)
          .then(res => {
            console.log(res);
            this.$router.push("/");
          })
          .catch(err => {            
              console.log(err);
              alert("회원가입 오류!");            
          });
        this.snackbar = true;
      }
    },
    resetValidation() {
      this.$refs.form.resetValidation();
    },
    reset() {
      this.$refs.form.reset();
    },
    emailcheck() {
      this.duplicate=true;
      http
        .post("/emailCheck", {
          email: this.credentials.email
        })
        .then(respone => {
          console.log(respone);
          if (respone.data.state == "succ") {
            alert("중복된 E-mail입니다. 다른 E-mail을 입력해주세요.");
            this.duplicate=false;
            this.credentials.email = "";
          } else {
            alert("사용 가능합니다.");
            this.duplicate = true;
          }
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  mounted() {
    this.$modal.hide("login-modal");
  }
};
</script>
<style scoped>
</style>