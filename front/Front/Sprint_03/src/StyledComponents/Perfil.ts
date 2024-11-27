import { Link } from "react-router-dom";
import styled from "styled-components";

export const ImagemFundo = styled.img`
@media screen and (min-width: 320px) and (max-width: 740px) {
    height: 50vw;
    width: 100%
}
    
@media screen and (min-width: 741px) and (max-width: 1280px) {
    height: 50vw;
    width: 100%;
}

@media (min-width: 1281px){
    height: 50vw;
    width: 100%;
}
`
export const ImagemUsuario = styled.img`
@media screen and (min-width: 320px) and (max-width: 740px) {
    height: 30vw;
    width: 30vw;
    border-radius: 50%;
    display: block;
    margin: auto;
    margin-top: -17vw;
}

@media screen and (min-width: 741px) and (max-width: 1280px) {
    height: 30vw;
    width: 30vw;
    border-radius: 50%;
    display: block;
    margin: auto;
    margin-top: -17vw;
}

@media (min-width: 1281px){
    height: 30vw;
    width: 30vw;
    border-radius: 50%;
    display: block;
    margin: auto;
    margin-top: -17vw;
}
 
`
export const ParagrafoBio = styled.p`
@media screen and (min-width: 320px) and (max-width: 740px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    text-align: center;
    font-size: 3vw;
    margin-top: 7vw;
    width:75%;
    margin-left: 12.5%;
    line-height: 4.5vw;
}

@media screen and (min-width: 741px) and (max-width: 1280px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    text-align: center;
    font-size: 3vw;
    margin-top: 7vw;
    width:75%;
    margin-left: 12.5%;
    line-height: 4.5vw;
}


@media (min-width: 1281px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    text-align: center;
    font-size: 3vw;
    margin-top: 7vw;
    width:75%;
    margin-left: 12.5%;
    line-height: 4.5vw;
}
`
export const Agendamentos = styled.h2`
@media screen and (min-width: 320px) and (max-width: 740px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: white;
    background-color: #007b9e;
    width: 30vw;
    padding-top: 1vw;
    padding-bottom: 1vw;
    padding-left: 3vw;
    border-top-right-radius: 5vw;
    margin-top: 10vw;
    font-size: 3vw;
}

@media screen and (min-width: 741px) and (max-width: 1280px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: white;
    background-color: #007b9e;
    width: 30vw;
    padding-top: 1vw;
    padding-bottom: 1vw;
    padding-left: 3vw;
    border-top-right-radius: 5vw;
    margin-top: 10vw;
    font-size: 3vw;
}

@media (min-width: 1281px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: white;
    background-color: #007b9e;
    width: 30vw;
    padding-top: 1vw;
    padding-bottom: 1vw;
    padding-left: 3vw;
    border-top-right-radius: 5vw;
    margin-top: 10vw;
    font-size: 3vw;
}


`
export const ComponenenteTextual = styled.p`
@media screen and (min-width: 320px) and (max-width: 740px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: black;
    margin-left: 4vw;
    margin-top: 5vw;
    font-size: 3.5vw;
    margin-bottom: 1vw;
}

@media screen and (min-width: 741px) and (max-width: 1280px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: black;
    margin-left: 4vw;
    margin-top: 5vw;
    font-size: 3.5vw;
    margin-bottom: 1vw;
}

@media (min-width: 1281px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: black;
    margin-left: 4vw;
    margin-top: 5vw;
    font-size: 3.5vw;
    margin-bottom: 1vw;
}


`
export const ComponenenteTextualComplementar = styled.p`
@media screen and (min-width: 320px) and (max-width: 740px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: black;
    margin-left: 70%;
    margin-top: 5vw;
    font-size: 3.5vw;
    padding-bottom: 1vw;
    position: relative;
    top: -43vw;
}
 

@media screen and (min-width: 741px) and (max-width: 1280px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: black;
    margin-left: 70%;
    margin-top: 5vw;
    font-size: 3.5vw;
    padding-bottom: 1vw;
    position: relative;
    top: -43vw;
}

@media (min-width: 1281px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    color: black;
    margin-left: 70%;
    margin-top: 5vw;
    font-size: 3.5vw;
    padding-bottom: 1vw;
    position: relative;
    top: -43vw;
}
`
export const Veiculos_ = styled(Link)`
@media screen and (min-width: 320px) and (max-width: 740px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    background-color: #18B7E4;
    color: white;
    height: 5vw;
    width: 30vw;
    padding-top: 1.15vw;
    padding-bottom: 1.15vw;
    text-decoration: none;
    font-size: 3vw;
    padding-left: 9.4vw;
    padding-right: 9.4vw;
    border-top-right-radius: 4vw;
    margin-left: 30vw;
    position: relative;
    top: -5vw;
    cursor: pointer;
}

@media screen and (min-width: 741px) and (max-width: 1280px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    background-color: #18B7E4;
    color: white;
    height: 5vw;
    width: 30vw;
    padding-top: 1.15vw;
    padding-bottom: 1.15vw;
    text-decoration: none;
    font-size: 3vw;
    padding-left: 9.4vw;
    padding-right: 9.4vw;
    border-top-right-radius: 4vw;
    margin-left: 30vw;
    position: relative;
    top: -4.5vw;
    cursor: pointer;
}

@media (min-width: 1281px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    background-color: #18B7E4;
    color: white;
    height: 5vw;
    width: 30vw;
    padding-top: 1.15vw;
    padding-bottom: 1.15vw;
    text-decoration: none;
    font-size: 3vw;
    padding-left: 9.4vw;
    padding-right: 9.4vw;
    border-top-right-radius: 4vw;
    margin-left: 30vw;
    position: relative;
    top: -4.5vw;
    cursor: pointer;
}
`
export const AutoPlus = styled(Link)`
@media screen and (min-width: 320px) and (max-width: 740px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    background-color: #18B7E4;
    color: white;
    height: 5vw;
    width: 30vw;
    padding-top: 1.15vw;
    padding-bottom: 1.15vw;
    text-decoration: none;
    font-size: 3vw;
    padding-left: 9.4vw;
    padding-right: 9.4vw;
    border-top-right-radius: 4vw;
    margin-left: -0vw;
    position: relative;
    top: -5vw;
}
    
@media screen and (min-width: 741px) and (max-width: 1280px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    background-color: #18B7E4;
    color: white;
    height: 5vw;
    width: 30vw;
    padding-top: 1.15vw;
    padding-bottom: 1.15vw;
    text-decoration: none;
    font-size: 3vw;
    padding-left: 9.4vw;
    padding-right: 9.4vw;
    border-top-right-radius: 4vw;
    margin-left: -0vw;
    position: relative;
    top: -4.5vw;
}

@media (min-width: 1281px) {
    font-family: "Red Hat Display", sans-serif;
    font-optical-sizing: auto;
    font-weight: normal;
    font-style: normal;
    background-color: #18B7E4;
    color: white;
    height: 5vw;
    width: 30vw;
    padding-top: 1.15vw;
    padding-bottom: 1.15vw;
    text-decoration: none;
    font-size: 3vw;
    padding-left: 9.4vw;
    padding-right: 9.4vw;
    border-top-right-radius: 4vw;
    margin-left: -0vw;
    position: relative;
    top: -4.5vw;
}
`
export const SetaVoltar = styled(Link)`
@media screen and (min-width: 320px) and (max-width: 740px) {
    img{
        height: 10vw;
        width: 10vw;
        position: relative;
        bottom: 197vw;
        margin-left: 2vw;
    }
}

@media screen and (min-width: 741px) and (max-width: 1280px) {
    img{
        height: 10vw;
        width: 10vw;
        position: relative;
        bottom: 197vw;
        margin-left: 2vw;
    }
}

@media (min-width: 1281px) {
    img{
    height: 10vw;
    width: 10vw;
    position: relative;
    bottom: 197vw;
    margin-left: 2vw;
    }

`