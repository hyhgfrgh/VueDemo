import axios from "axios";

const API_URL = '/api/auth'

export const login = async (credential) =>{
    const response = await axios.post('/api/auth/login',credential)
    const token = response.data.token
    localStorage.setItem('user_token',token)
    return response.data;

}