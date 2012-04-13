/*
 * The MIT License
 *
 * Copyright 2012 J. David Mendoza <jdmendoza@um.edu.mx>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mx.edu.um.mateo.general.web;

import java.text.NumberFormat;
import mx.edu.um.mateo.Constantes;
import mx.edu.um.mateo.general.dao.*;
import mx.edu.um.mateo.general.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Controller
@RequestMapping("/prueba")
public class PruebaController {

    @Autowired
    private UnionDao unionDao;
    @Autowired
    private RolDao rolDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private AsociacionDao asociacionDao;

    @RequestMapping
    public String inicia() {
        return "/prueba/index";
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public String guarda(
            @RequestParam String username,
            @RequestParam String password) {
        Union union = new Union("UM", Constantes.STATUS_ACTIVO);
        union = unionDao.crea(union);
        Rol rol = new Rol("ROLE_ADMIN");
        rol = rolDao.crea(rol);
        Usuario usuario = new Usuario(
                username,
                password,
                "Admin",
                "User");
        Long asociacionId = 0l;
        actualizaUsuario:
        for (Asociacion asociacion : union.getAsociaciones()) {
            asociacionId = asociacion.getId();
            break actualizaUsuario;
        }
        usuarioDao.crea(usuario, asociacionId, new String[]{rol.getAuthority()});
        rol = new Rol("ROLE_UNI");
        rolDao.crea(rol);
        rol = new Rol("ROLE_ASO");
        rolDao.crea(rol);
        rol = new Rol("ROLE_USER");
        rolDao.crea(rol);

        union = new Union("TEST", Constantes.STATUS_ACTIVO);
        unionDao.crea(union, usuario);

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumIntegerDigits(2);
        nf.setMaximumFractionDigits(0);
        nf.setGroupingUsed(false);
        for (int i = 1; i <= 29; i++) {
            String numero = nf.format(i);
            StringBuilder sb = new StringBuilder();
            sb.append("TST-").append(numero);
            Asociacion asociacion = new Asociacion(sb.toString(), Constantes.STATUS_ACTIVO, union);
            asociacionDao.crea(asociacion);
        }

        return "redirect:/";
    }
}
