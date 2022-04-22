package app.inmobiliaria.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.inmobiliaria.api.entity.SubMenu;

public interface SubMenuRepository extends JpaRepository<SubMenu, Long> {


}


