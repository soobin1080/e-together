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
          v-model="name"
          :rules="nameRules"
          label="nickname"
          counter="20,"
          required
          autocomplete="name"
        ></v-text-field>

        <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>

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
        ></v-text-field>

        <v-text-field
          v-model="phone"
          label="phone"
          :rules="[rules.required, rules.is_num]"
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
export default {
  name: "SignUpPage",
  components: {
    ImgBanner
  },
  directives: {
    ResizeText
  },
  data: () => ({
    name: "",
    email: "",
    nickname: "",
    phone: "",
    show1: false,
    valid: true,
    password: "",
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
    validate() {
      if (this.$refs.form.validate()) {
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
    this.$modal.hide("login-modal");
  }
};
</script>
<style scoped>
</style>