import styled from "styled-components";

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 120vh;
  background-color: #18B7E4;

  @media screen and (min-width: 320px) and (max-width: 740px) {
    padding: 20vw;
  }

  @media (min-width: 741px) and (max-width: 1280px) {
    padding: 20vw;
  }

  @media (min-width: 1281px) {
    padding: 20vw;
  }
`;
export const DivAzulMedio = styled.div`
  height: 55vw;
  background-color: #3ebde1;
  border-radius: 1.6vw;
  width: 70%;
` 
 export const Logo = styled.img`
  width: 50px;
  padding-top: 5vw;
  padding-left: 5vw;
  

  @media screen and (min-width: 320px) and (max-width: 740px) {
    width: 11vw;
  }

  @media (min-width: 741px) and (max-width: 1280px) {
    width: 11vw;
  }

  @media (min-width: 1281px) {
    width: 11vw;
  }
`;
export const Title = styled.h1`
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: bold;
    font-style: normal;
    text-align: center;
    padding-top: 5vw;
    color: white;
    margin-bottom: 3vw;
  @media (max-width: 740px) {
    font-size: 3vw;
  }
  @media (min-width: 741px) and (max-width: 1280px) {
    font-size: 3vw;
  }
  @media (min-width: 1281px) {
    font-size: 3vw;
  }
`;
export const Input = styled.input`
  width: 70%;
  padding: 1vw;
  text-align: center;
  margin-left: 10%;
  margin-bottom: 2vw;
  border-radius: 1vw;
  border: none;
  font-size: 2vw;
  @media (max-width: 740px) {
    width: 80%;
  }
  @media (min-width: 741px) and (max-width: 1280px) {
    width: 80%;
  }
  @media (min-width: 1281px) {
    width: 80%;
  }
`;
export const Divisao = styled.div`
    @media screen and (min-width: 320px) and (max-width: 740px) {
        width: 80%;
        height: 0.3vw;
        margin-left: 10%;
        margin-top: 2%;
        background-color: white;
        margin-bottom: 2vw;
    }
        @media screen and (min-width: 740px) and (max-width: 1280px) {
        width: 80%;
        height: 0.3vw;
        margin-left: 10%;
        margin-top: 2%;
        background-color: white;
        margin-bottom: 2vw;
    }
        @media (min-width: 1281px) {
        width: 80%;
        height: 0.3vw;
        margin-left: 10%;
        margin-top: 2%;
        background-color: white;
        margin-bottom: 2vw;
    }
`
export const Button = styled.button`
  background-color: lightgray;
  border: none;
  width: 80%;
  margin-left: 10%;
  cursor: pointer;
  border-radius: 1vw;
  margin-bottom: 2vw;
  font-size: 2vw;

  @media (max-width: 740px) {
    padding: 1vw 1vw;
  }

  @media (min-width: 741px) and (max-width: 1280px) {
    padding: 1vw 1vw;
  }

  @media (min-width: 1281px) {
    padding: 1vw 1vw;
  }
`
export const BackButton = styled(Button)`
  background-color: lightgray;
`
