import styled from "styled-components";

export const Espacadores = styled.div`
@media screen and (min-width: 320px) and (max-width: 740px) {
    .espacadorEsquerdo{
        background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 10%;
        margin-top: -22vw;
      }
    .espacadorDireito{
        background-color: black;
        height: 0.4vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 59%;
        margin-top: -1vw;
      }
}
@media screen and (min-width: 741px) and (max-width: 1280px) {
    .espacadorEsquerdo{
        background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 10%;
        margin-top: -22vw;
      }
    .espacadorDireito{
        background-color: black;
        height: 0.4vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 59%;
        margin-top: -1vw;
      }
}
@media (min-width: 1281px) {
    .espacadorEsquerdo{
        background-color: black;
        height: 0.5vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 10%;
        margin-top: -22vw;
      }
    .espacadorDireito{
        background-color: black;
        height: 0.4vw;
        width: 30%;
        border-radius: 1vw;
        margin-left: 59%;
        margin-top: -1vw;
      }
}
`