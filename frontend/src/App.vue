<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <TopBar v-if="isLoggedIn"></TopBar>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
var ws = new WebSocket("ws://localhost:8088/socket/test");
 
ws.onmessage = (e) => {
    console.log("got message: ", e.data)
    alert(e.data)
};
 
ws.onopen = (e) => {
    console.log("opened: ", e.data)
}
 
ws.onclose = (e) => {
    console.log("closed: ", e.data)
}
 
ws.onerror = (e) => {
    console.log("error: ", e.data)
}

import TopBar from "./components/TopBar";
export default {
  name: "App",
  components: { TopBar },
  data: () => ({
    //
  }),
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
  },
};
</script>
