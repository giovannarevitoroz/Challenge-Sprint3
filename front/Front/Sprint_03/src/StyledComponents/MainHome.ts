import { Link } from "react-router-dom";
import styled from "styled-components";

interface HanburguerBotaoProps {
  marginTop?: string;
}
export const HanburguerBotao = styled.div<HanburguerBotaoProps>`
  @media screen and (min-width: 320px) and (max-width: 740px) {
    width: 10vw;
    height: 1.2vw;
    background-color: black;
    border-radius: 2vw;
    margin-bottom: 2vw;
    margin-left: 5vw;
    cursor: pointer;
    margin-top: ${props => props.marginTop || '0'};
  }

    @media screen and (min-width: 741px) and (max-width: 1280px) {
    width: 10vw;
    height: 1.2vw;
    background-color: Black;
    border-radius: 2vw;
    margin-bottom: 2vw;
    margin-left: 5vw;
    cursor: pointer;
    margin-top: ${props => props.marginTop || '0'};
  }

     @media (min-width: 1281px) {
    width: 10vw;
    height: 1.2vw;
    background-color: Black;
    border-radius: 2vw;
    margin-bottom: 2vw;
    margin-left: 5vw;
    cursor: pointer;
    margin-top: ${props => props.marginTop || '0'};
  }
`
interface HanburguerMenuProps {
  menuAberto: boolean;
}
export const HanburguerMenu = styled.div<HanburguerMenuProps>`
  position: absolute;
  @media screen and (min-width: 320px) and (max-width: 740px) {
    top: 0.1vw;
    right: 60vw;
    background: linear-gradient(to bottom, #18B7E4, #006581);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    width: 40vw;
    height: 50dvw;
    display: ${props => (props.menuAberto ? 'block' : 'none')};
    border-top-left-radius: 9vw;
    border-bottom-right-radius: 8vw;
    border-bottom-left-radius: 3vw;
    transition: all 0.3s ease;
  }

    @media screen and (min-width: 741px) and (max-width: 1280px) {
    top: 0.1vw;
    right: 60vw;
    background: linear-gradient(to bottom, #18B7E4, #006581);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    width: 40vw;
    height: 50dvw;
    display: ${props => (props.menuAberto ? 'block' : 'none')};
    border-top-left-radius: 9vw;
    border-bottom-right-radius: 8vw;
    border-bottom-left-radius: 3vw;
    transition: all 0.3s ease;
  }

     @media (min-width: 1281px) {
    top: 0.1vw;
    right: 60vw;
    background: linear-gradient(to bottom, #18B7E4, #006581);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    width: 40vw;
    height: 50dvw;
    display: ${props => (props.menuAberto ? 'block' : 'none')};
    border-top-left-radius: 9vw;
    border-bottom-right-radius: 8vw;
    border-bottom-left-radius: 3vw;
    transition: all 0.3s ease;
  }
`
export const ListaUL = styled.ul`
  @media screen and (min-width: 320px) and (max-width: 740px) {
    list-style-type: none;
  }
  @media screen and (min-width: 741px) and (max-width: 1280px) {
    list-style-type: none;
  }
  @media (min-width: 1281px) {
    list-style-type: none;
  }
`
export const ListaLI = styled.li`
  @media screen and (min-width: 320px) and (max-width: 740px) {
    height: 7vw;
    color: black;
    &:hover {
      text-decoration: underline;
      text-decoration-color: black;
    }
  }
  @media screen and (min-width: 741px) and (max-width: 1280px) {
    height: 7vw;
    color: black;
    &:hover {
      text-decoration: underline;
      text-decoration-color: black;
    }
  }
    @media (min-width: 1281px) {
    height: 7vw;
    color: black;
    &:hover {
      text-decoration: underline;
      text-decoration-color: black;
    }
  }
`
export const ListaMenu = styled(Link)`
  @media screen and (min-width: 320px) and (max-width: 740px) {
    text-decoration: none;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: weight;
    font-style: normal;
    font-size: 3vw;
    margin-left: 8vw;
    color: white;
  }
  @media screen and (min-width: 741px) and (max-width: 1280px) {
    text-decoration: none;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: weight;
    font-style: normal;
    font-size: 3vw;
    margin-left: 8vw;
    color: white;
  }
  @media (min-width: 1281px) {
    text-decoration: none;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: weight;
    font-style: normal;
    font-size: 3vw;
    margin-left: 8vw;
    color: white;
  }
`;
export const FormH2 = styled.h2`
  @media screen and (min-width: 320px) and (max-width: 740px) {
    color: white;
    font-size: 4vw;
    width: 40vw;
    text-align: center;
    margin-top: 2vw;
    margin-bottom: 3vw;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-style: normal;
  }
  @media screen and (min-width: 741px) and (max-width: 1280px) {
    color: white;
    font-size: 4vw;
    width: 40vw;
    text-align: center;
    margin-top: 2vw;
    margin-bottom: 3vw;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-style: normal;
  }
  @media (min-width: 1280px) {
    color: white;
    font-size: 4vw;
    width: 40vw;
    text-align: center;
    margin-top: 2vw;
    margin-bottom: 3vw;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-style: normal;
  }
`;
export const ImagemSlide = styled.section`
@media screen and (min-width: 320px) and (max-width: 740px) {
background: linear-gradient(to bottom, white, black);
img{
height: 100vw;
width: 85%;
border-radius: 5vw;
margin: 7.5% 0% 20% 7.5%;
}
}
@media screen and (min-width: 741px) and (max-width: 1280px) {
background: linear-gradient(to bottom, white, black);
img{
height: 100vw;
width: 85%;
border-radius: 5vw;
margin: 7.5% 0% 20% 7.5%;
}
}
@media (min-width: 1281px) {
background: linear-gradient(to bottom, white, black);
img{
height: 100vw;
width: 85%;
border-radius: 5vw;
margin: 7.5% 0% 20% 7.5%;
}
}
`
export const Section_four = styled.section`
@media screen and (min-width: 320px) and (max-width: 740px) {
  background: linear-gradient(to bottom, #0D657E, #18B7E4);
  p{
   font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: bolder;
    font-style: normal;
    font-size: 5vw;
    width: 80%;
    text-align: center;
    margin-left: 10%; 
    padding-top: 30vw;
    color: white;
  }
    img{
      height: 70vw;
      width: 55vw;
      margin-left: 30%;
      margin-top: 11%;
    }
}
@media screen and (min-width: 741px) and (max-width: 1280px) {
  background: linear-gradient(to bottom, #0D657E, #18B7E4);
  p{
   font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: bolder;
    font-style: normal;
    font-size: 5vw;
    width: 80%;
    text-align: center;
    margin-left: 10%; 
    padding-top: 30vw;
    color: white;
  }
    img{
      height: 70vw;
      width: 55vw;
      margin-left: 30%;
      margin-top: 11%;
    }
}
@media (min-width: 1281px) {
  background: linear-gradient(to bottom, #0D657E, #18B7E4);
  p{
   font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: bolder;
    font-style: normal;
    font-size: 5vw;
    width: 80%;
    text-align: center;
    margin-left: 10%; 
    padding-top: 30vw;
    color: white;
  }
    img{
      height: 70vw;
      width: 55vw;
      margin-left: 30%;
      margin-top: 11%;
    }
}
`
export const Section_tree = styled.section`
@media screen and (min-width: 320px) and (max-width: 740px) {
background: linear-gradient(to bottom, #18B7E4, #0D657E);
border-top-left-radius: 10vw;
border-top-right-radius: 10vw;
  P{
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: bolder;
    font-style: normal;
    font-size: 8vw;
    text-align: center;
    width: 70%;
    margin-left: 15%;
    margin-top: 20%;
    padding-top: 24%;
    color: white;
    margin-bottom: 25%;
  }
  A{
    margin-left: 10%;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: weight;
    font-style: normal;
    text-decoration: none;
    background-color: black;
    color: white;       
    font-size: 5vw;
    padding-left: 15vw;
    padding-right: 15vw;
    padding-top: 1vw;
    padding-bottom: 1vw;
    border-radius: 10vw;
    margin-left: 32vw;
  }
    a:hover{
    background-color: darkgray;}
}
@media screen and (min-width: 741px) and (max-width: 1280px) {
background: linear-gradient(to bottom, #18B7E4, #0D657E);
border-top-left-radius: 10vw;
border-top-right-radius: 10vw;
  P{
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: bolder;
    font-style: normal;
    font-size: 8vw;
    text-align: center;
    width: 70%;
    margin-left: 15%;
    margin-top: 20%;
    padding-top: 24%;
    color: white;
    margin-bottom: 25%;
  }
  A{
    margin-left: 10%;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: weight;
    font-style: normal;
    text-decoration: none;
    background-color: black;
    color: white;       
    font-size: 5vw;
    padding-left: 15vw;
    padding-right: 15vw;
    padding-top: 1vw;
    padding-bottom: 1vw;
    border-radius: 10vw;
    margin-left: 32vw;
  }
    a:hover{
    background-color: darkgray;}
}
@media (min-width: 1281px) {
background: linear-gradient(to bottom, #18B7E4, #0D657E);
border-top-left-radius: 10vw;
border-top-right-radius: 10vw;
  P{
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: bolder;
    font-style: normal;
    font-size: 8vw;
    text-align: center;
    width: 70%;
    margin-left: 15%;
    margin-top: 20%;
    padding-top: 24%;
    color: white;
    margin-bottom: 25%;
  }
  A{
    margin-left: 10%;
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: weight;
    font-style: normal;
    text-decoration: none;
    background-color: black;
    color: white;       
    font-size: 5vw;
    padding-left: 15vw;
    padding-right: 15vw;
    padding-top: 1vw;
    padding-bottom: 1vw;
    border-radius: 10vw;
    margin-left: 32vw;
  }
    a:hover{
    background-color: darkgray;}
}
`
export const Section_two = styled.section`
@media screen and (min-width: 320px) and (max-width: 740px) {
background: linear-gradient(to bottom, black, white);
p{
font-size: 7vw;
text-align: center;
width: 80%;
margin-left: 10%;
font-family: "Red Hat Display", sans-serif;
font-optical-sizing: auto;
font-weight: weight;
font-style: normal;
margin-bottom: 10%;
color: white;
}
img{
height: 45vw;
width: 90vw;
margin-left: 5vw;
margin-bottom: 15%;
}
a{
font-family: "Red Hat Display", sans-serif;
font-optical-sizing: auto;
font-weight: light;
font-style: normal;
text-decoration: none;
color: white;
background-color: #18B7E4;
padding-left: 10vw;
padding-right: 10vw;
font-size: 6vw;
padding-top: 1vw;
padding-bottom: 1vw;
border-radius: 5vw;
margin-left: 28vw;
}
a:hover{
background-color: #006581;
}
}
@media screen and (min-width: 741px) and (max-width: 1280px) {
background: linear-gradient(to bottom, black, white);
p{
font-size: 7vw;
text-align: center;
width: 80%;
margin-left: 10%;
font-family: "Red Hat Display", sans-serif;
font-optical-sizing: auto;
font-weight: weight;
font-style: normal;
margin-bottom: 10%;
color: white;
}
img{
height: 45vw;
width: 90vw;
margin-left: 5vw;
margin-bottom: 15%;
}
a{
font-family: "Red Hat Display", sans-serif;
font-optical-sizing: auto;
font-weight: light;
font-style: normal;
text-decoration: none;
color: white;
background-color: #18B7E4;
padding-left: 10vw;
padding-right: 10vw;
font-size: 6vw;
padding-top: 1vw;
padding-bottom: 1vw;
border-radius: 5vw;
margin-left: 28vw;
}
a:hover{
background-color: #006581;
}
}
@media (min-width: 1281px) {
background: linear-gradient(to bottom, black, white);
p{
font-size: 7vw;
text-align: center;
width: 80%;
margin-left: 10%;
font-family: "Red Hat Display", sans-serif;
font-optical-sizing: auto;
font-weight: weight;
font-style: normal;
margin-bottom: 10%;
color: white;
}
img{
height: 45vw;
width: 90vw;
margin-left: 5vw;
margin-bottom: 15%;
}
a{
font-family: "Red Hat Display", sans-serif;
font-optical-sizing: auto;
font-weight: light;
font-style: normal;
text-decoration: none;
color: white;
background-color: #18B7E4;
padding-left: 10vw;
padding-right: 10vw;
font-size: 6vw;
padding-top: 1vw;
padding-bottom: 1vw;
border-radius: 5vw;
margin-left: 28vw;
}
a:hover{
background-color: #006581;
}
}
`