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
          v-model="nickname"
          :rules="nameRules"
          label="nickname"
          counter="20,"
          required
          readonly
        ></v-text-field>

        <v-text-field v-model="email" :rules="emailRules" label="E-mail" required readonly></v-text-field>

        <v-text-field
          v-model="password"
          :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.required, rules.min_8]"
          :type="show1 ? 'text' : 'password'"
          name="input-10-1"
          label="password"
          hint="At least 8 characters"
          counter="16"
          @click:append="show1 = !show1"
          readonly
        ></v-text-field>

        <v-col class="d-flex" style="padding-left:0;">
          <v-select
            style=" width: 20%;"
            :items="phoneNumbers"
            label="phone"
            :rules="[rules.required]"
            readonly
          ></v-select>

          <v-text-field
            v-model="num_2"
            style="width: 39%; padding-left:1%;"
            :rules="[rules.required, rules.min_4, rules.is_num]"
            readonly
          ></v-text-field>
          <v-text-field
            v-model="num_3"
            style="width: 39%; padding-left:1%;"
            :rules="[rules.required, rules.min_4, rules.is_num]"
            readonly
          ></v-text-field>
        </v-col>

        <v-btn
          :disabled="!valid"
          color="success"
          class="mr-4"
          @click="validate"
          style="float:right"
        >수정하기</v-btn>
      </v-container>
    </v-form>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import ResizeText from "vue-resize-text";
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
    user: {
      name: "",
      email: "",
      nickname: "",
      phone: "",
      password: ""
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
      getUserInfoUrl = `/login/${UserEmail}/`; // 유저 정보 가져올 url
      axios.post(getUserInfoUrl).then(response => {
        this.user = response.data; // 가져온
      });
    }
  },

  mounted() {
    this.$modal.hide("login-modal");
    this.email = sessionStorage.getItem("email");
    this.getUserInfo(this.email);
  }
};
</script>