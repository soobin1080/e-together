import axios from "axios";

export default axios.create({
 baseURL: "http://13.209.9.53:8197/together/api"

//  headers: {
//   "Content-type": "application/xml",
// }
});