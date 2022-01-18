package com.blue.wallet.controller;

import com.blue.wallet.controller.transport.response.DashboardResponseDTO;
import com.blue.wallet.controller.uri.DashboardURI;
import com.blue.wallet.security.JwtTokenUtil;
import com.blue.wallet.service.DashboardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DashboardController {

    @Autowired
    private JwtTokenUtil tokenUtil;
    
    @Autowired
    private DashboardService dashboardService;

    @GetMapping(DashboardURI.PESQUISAR_DASHBORD_POR_DATA)
    @ApiOperation(value = "Endpoint para coleta de informações da página de dashboard")
    public ResponseEntity<DashboardResponseDTO> pesquisarInformacoesDashboard (@RequestHeader("Authorization") String token,
                                                                               @PathVariable String data) {
        String idUsuario = tokenUtil.getIdUsuariofromToken(tokenUtil.cleanToken(token));

        DashboardResponseDTO dashboardResponseDTO = dashboardService.pesquisarInformacoesDashboard(Integer.parseInt(idUsuario), data);

        return ResponseEntity.status(HttpStatus.OK).body(dashboardResponseDTO);
    }
}