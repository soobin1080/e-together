<template>
  <div>
  <v-text-field :rules="rules"></v-text-field>
  <v-form
    ref="form"
    v-model="valid"
    lazy-validation
  >
  <v-container fluid>
  <h1 class="text-center mt-5">회원가입</h1>

    <v-text-field
      v-model="name"
      :rules="nameRules"
      label="name"
      counter=10,
      required
    ></v-text-field>

    <v-text-field
      v-model="nickname"
      :rules="nameRules"
      label="Nickname"
      counter=10,
      required
    ></v-text-field>

    <v-text-field
      v-model="email"
      :rules="emailRules"
      label="E-mail"
      required
    ></v-text-field>

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
   
 

    

    <v-col class="d-flex" style="padding-left:0;">
        <v-select
          style=" width: 20%;"
          :items="phoneNumbers"
          label="phone"
          :rules="[rules.required]"
        ></v-select>

        <v-text-field
          v-model="num_2"  
          style="width: 39%; padding-left:1%;"
          :rules="[rules.required, rules.min_4, rules.is_num]"
          ></v-text-field>
        <v-text-field
          v-model="num_3"
          style="width: 39%; padding-left:1%;"
          :rules="[rules.required, rules.min_4, rules.is_num]">
        </v-text-field>
    </v-col>

    <!-- <v-select
      v-model="select"
      :items="items"
      :rules="[v => !!v || 'Item is required']"
      label="Item"
      required
    ></v-select>

    <v-checkbox
      v-model="checkbox"
      :rules="[v => !!v || 'You must agree to continue!']"
      label="Do you agree?"
      required
    ></v-checkbox> -->

    <v-btn
      :disabled="!valid"
      color="success"
      class="mr-4"
      @click="validate"
    >
      가입!
    </v-btn>

    <!-- <v-btn
      color="error"
      class="mr-4"
      @click="reset"
    >
      Reset Form
    </v-btn>

    <v-btn
      color="warning"
      @click="resetValidation"
    >
      Reset Validation
    </v-btn> -->
    </v-container>
  </v-form>
</div>
</template>

<script>
export default {
  name: 'SignInPage',
  data: () =>  ({
    name: "",
    email: "",
    nickname: "",
    num_1: "",
    num_2: "",
    num_3: "",
    show1: false,
    valid: true,
    password: "",
    rules: {
      required: value => !!value || 'Required.',
      min_8: v => v.length >= 8 || 'Min 8 characters',
      min_4: v=> v.length >= 4 || 'Min 8 characters',
      is_num: v => !isNaN(v) || 'Please input number',
      emailMatch: () => ('The email and password you entered don\'t match'),
    },
    nameRules: [
      v => !!v || 'Name is required',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ],
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
    ],
    phoneNumbers: [
      '010', '011'
    ]
   
  }),
  methods: {
    validate () {
        if (this.$refs.form.validate()) {
          this.snackbar = true
        }
      },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
  },
  mounted(){
    this.$modal.hide('login-modal');
  }

}
</script>