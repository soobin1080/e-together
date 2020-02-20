<template>
  <v-container>
    <h1 class="text-center">User List</h1>
    <br>
    <ul class="list-group">
      <li v-for="user in computedUserList"
        v-if="" 
        @click="goUserDetail(user.email)"
        class="list-group-item list-group-item-action" 
        :style="isDeleteUser(user)">
        {{user.name}}
        {{user.email}}
        <p v-if="user.withdraw === 1" class="d-inline">
          <v-icon class="text-white">clear</v-icon>
          탈퇴 유저
        </p>
        </li>
    </ul>
  </v-container>
</template>

<script>
import http from "../http-common";
import UserList from "../components/UserList";
import User from "./User"
export default {
  name: "UserList",
  components: {
    User
  },
  props: {
    propsUserList: {
      type: Array
    }
  },
  methods: {
    goUserDetail(userEmail) {
      console.log('goUserDetail' + userEmail)
      // http
      //   .get()
    },
    isDeleteUser(user) {
      if (user.withdraw === 1) {
        return "background-color:#E56449; color:white;"
      }
    }
  },

  data() {
    return {
      userList : [],
    };
  },

  computed: {
    computedUserList() {
      return this.propsUserList
    }
  },

};
</script>
<style scoped>
  li {
    cursor: pointer
  }
</style>
