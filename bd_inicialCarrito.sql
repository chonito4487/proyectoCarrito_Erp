
CREATE SEQUENCE depositos_di_depo_seq;

CREATE TABLE depositos (
                id_depo INTEGER NOT NULL DEFAULT nextval('depositos_di_depo_seq'),
                nombre_depo VARCHAR(50) NOT NULL,
                CONSTRAINT depositos_pk PRIMARY KEY (id_depo)
);


ALTER SEQUENCE depositos_di_depo_seq OWNED BY depositos.id_depo;

CREATE SEQUENCE categoria_id_cate_seq;

CREATE TABLE categoria (
                id_cate INTEGER NOT NULL DEFAULT nextval('categoria_id_cate_seq'),
                nombre_cat VARCHAR(60) NOT NULL,
                CONSTRAINT categoria_pk PRIMARY KEY (id_cate)
);


ALTER SEQUENCE categoria_id_cate_seq OWNED BY categoria.id_cate;

CREATE SEQUENCE productos_id_pro_seq;

CREATE TABLE productos (
                id_pro INTEGER NOT NULL DEFAULT nextval('productos_id_pro_seq'),
                nombre_pro VARCHAR(80) NOT NULL,
                cod_prod VARCHAR(40) NOT NULL,
                descripcion VARCHAR(250) NOT NULL,
                imagen_pro VARCHAR(250) NOT NULL,
                precio_compra INTEGER NOT NULL,
                precio_venta INTEGER NOT NULL,
                id_cate INTEGER NOT NULL,
                estado_compra BOOLEAN NOT NULL,
                estado_pro BOOLEAN NOT NULL,
                CONSTRAINT productos_pk PRIMARY KEY (id_pro)
);


ALTER SEQUENCE productos_id_pro_seq OWNED BY productos.id_pro;

CREATE SEQUENCE stock_id_stock_seq;

CREATE TABLE stock (
                id_stock INTEGER NOT NULL DEFAULT nextval('stock_id_stock_seq'),
                id_pro INTEGER NOT NULL,
                id_depo INTEGER NOT NULL,
                catidad INTEGER NOT NULL,
                CONSTRAINT stock_pk PRIMARY KEY (id_stock)
);


ALTER SEQUENCE stock_id_stock_seq OWNED BY stock.id_stock;

CREATE SEQUENCE departamentos_id_seq;

CREATE TABLE departamentos (
                id_dep INTEGER NOT NULL DEFAULT nextval('departamentos_id_seq'),
                nombre_dep VARCHAR(50) NOT NULL,
                CONSTRAINT departamentos_pk PRIMARY KEY (id_dep)
);


ALTER SEQUENCE departamentos_id_seq OWNED BY departamentos.id_dep;

CREATE SEQUENCE ciudades_id_ciu_seq;

CREATE TABLE ciudades (
                id_ciu INTEGER NOT NULL DEFAULT nextval('ciudades_id_ciu_seq'),
                nombre_ciu VARCHAR(50) NOT NULL,
                id_dep INTEGER NOT NULL,
                CONSTRAINT ciudades_pk PRIMARY KEY (id_ciu)
);


ALTER SEQUENCE ciudades_id_ciu_seq OWNED BY ciudades.id_ciu;

CREATE SEQUENCE usuarios_id_usu_seq;

CREATE TABLE usuarios (
                id_usu INTEGER NOT NULL DEFAULT nextval('usuarios_id_usu_seq'),
                nombre_usu VARCHAR(50) NOT NULL,
                apellido_usu VARCHAR(50) NOT NULL,
                telefono_usu VARCHAR(30) NOT NULL,
                username VARCHAR(50) NOT NULL,
                password VARCHAR(250) NOT NULL,
                email_usu VARCHAR(150) NOT NULL,
                rol_usu VARCHAR(30) NOT NULL,
                id_ciu INTEGER NOT NULL,
                estado_cli BOOLEAN NOT NULL,
                CONSTRAINT usuarios_pk PRIMARY KEY (id_usu)
);


ALTER SEQUENCE usuarios_id_usu_seq OWNED BY usuarios.id_usu;

CREATE SEQUENCE alta_producto_id_alta_pro_seq;

CREATE TABLE alta_producto (
                id_alta_pro INTEGER NOT NULL DEFAULT nextval('alta_producto_id_alta_pro_seq'),
                id_usu INTEGER NOT NULL,
                fecha_solicitud DATE NOT NULL,
                comentario VARCHAR(250) NOT NULL,
                estado VARCHAR(20) NOT NULL,
                CONSTRAINT alta_producto_pk PRIMARY KEY (id_alta_pro)
);


ALTER SEQUENCE alta_producto_id_alta_pro_seq OWNED BY alta_producto.id_alta_pro;

CREATE SEQUENCE alta_producto_det_id_det_alta_prod_seq;

CREATE TABLE alta_producto_det (
                id_det_alta_prod INTEGER NOT NULL DEFAULT nextval('alta_producto_det_id_det_alta_prod_seq'),
                id_alta_pro INTEGER NOT NULL,
                nombre  VARCHAR(80) NOT NULL,
                descripcion VARCHAR(250) NOT NULL,
                cod_producto VARCHAR(40) NOT NULL,
                 imagen_pro_alta VARCHAR(250) NOT NULL,
                id_cate INTEGER NOT NULL,
                CONSTRAINT alta_producto_det_pk PRIMARY KEY (id_det_alta_prod)
);


ALTER SEQUENCE alta_producto_det_id_det_alta_prod_seq OWNED BY alta_producto_det.id_det_alta_prod;

CREATE SEQUENCE pedido_compra_id_ped_comp_seq;

CREATE TABLE pedido_compra (
                id_ped_comp INTEGER NOT NULL DEFAULT nextval('pedido_compra_id_ped_comp_seq'),
                id_usu INTEGER NOT NULL,
                id_depo INTEGER NOT NULL,
                fecha_ped_comp DATE NOT NULL,
                observacion_ped_comp VARCHAR(150) NOT NULL,
                estado_ped_comp VARCHAR(20) NOT NULL,
                CONSTRAINT pedido_compra_pk PRIMARY KEY (id_ped_comp)
);


ALTER SEQUENCE pedido_compra_id_ped_comp_seq OWNED BY pedido_compra.id_ped_comp;

CREATE SEQUENCE det_ped_compra_id_det_ped_comp_seq;

CREATE TABLE det_ped_compra (
                id_det_ped_comp INTEGER NOT NULL DEFAULT nextval('det_ped_compra_id_det_ped_comp_seq'),
                id_ped_comp INTEGER NOT NULL,
                id_pro INTEGER NOT NULL,
                cantidad_dep_comp INTEGER NOT NULL,
                CONSTRAINT det_ped_compra_pk PRIMARY KEY (id_det_ped_comp)
);


ALTER SEQUENCE det_ped_compra_id_det_ped_comp_seq OWNED BY det_ped_compra.id_det_ped_comp;

CREATE SEQUENCE proveedores_id_pro_seq;

CREATE TABLE proveedores (
                id_prov INTEGER NOT NULL DEFAULT nextval('proveedores_id_pro_seq'),
                razon_social VARCHAR(120) NOT NULL,
                ruc_prov VARCHAR(50) NOT NULL,
                direccion_prov VARCHAR(150) NOT NULL,
                telefono_prov VARCHAR(30) NOT NULL,
                id_ciu INTEGER NOT NULL,
                CONSTRAINT proveedores_pk PRIMARY KEY (id_prov)
);


ALTER SEQUENCE proveedores_id_pro_seq OWNED BY proveedores.id_prov;

CREATE SEQUENCE presupuestos_id_pres_seq;

CREATE TABLE presupuestos (
                id_pres INTEGER NOT NULL DEFAULT nextval('presupuestos_id_pres_seq'),
                id_ped_comp INTEGER NOT NULL,
                id_prov INTEGER NOT NULL,
                fecha_solicitud_pre DATE NOT NULL,
                fecha_resp_sol DATE NOT NULL,
                estado_pre VARCHAR(20) NOT NULL,
                CONSTRAINT presupuestos_pk PRIMARY KEY (id_pres)
);


ALTER SEQUENCE presupuestos_id_pres_seq OWNED BY presupuestos.id_pres;

CREATE SEQUENCE orden_compra_id_ord_comp_seq;

CREATE TABLE orden_compra (
                id_ord_comp INTEGER NOT NULL DEFAULT nextval('orden_compra_id_ord_comp_seq'),
                id_pres INTEGER NOT NULL,
                fecha_ord_comp DATE NOT NULL,
                estado_ord_comp VARCHAR(20) NOT NULL,
                CONSTRAINT orden_compra_pk PRIMARY KEY (id_ord_comp)
);


ALTER SEQUENCE orden_compra_id_ord_comp_seq OWNED BY orden_compra.id_ord_comp;

CREATE SEQUENCE recepcion_id_recep_seq;

CREATE TABLE recepcion (
                id_rec INTEGER NOT NULL DEFAULT nextval('recepcion_id_recep_seq'),
                id_usu INTEGER NOT NULL,
                id_ord_comp INTEGER NOT NULL,
                fecha_rec DATE NOT NULL,
                 num_fac VARCHAR(35) NOT NULL,
                fecha_fact DATE NOT NULL,
                total_factura INTEGER NOT NULL,
                total_recibido INTEGER NOT NULL,
                estado_rec VARCHAR(20) NOT NULL,
                diferencia INTEGER NOT NULL,
                CONSTRAINT recepcion_pk PRIMARY KEY (id_rec)
);


ALTER SEQUENCE recepcion_id_recep_seq OWNED BY recepcion.id_rec;

CREATE SEQUENCE nota_credito_id_not_cre_seq;

CREATE TABLE nota_credito (
                id_not_cre INTEGER NOT NULL DEFAULT nextval('nota_credito_id_not_cre_seq'),
                nro_nota VARCHAR(20) NOT NULL,
                fecha DATE NOT NULL,
                id_rec INTEGER NOT NULL,
                id_prov INTEGER NOT NULL,
                monto_total INTEGER NOT NULL,
                motivo VARCHAR(250) NOT NULL,
                CONSTRAINT nota_credito_pk PRIMARY KEY (id_not_cre)
);


ALTER SEQUENCE nota_credito_id_not_cre_seq OWNED BY nota_credito.id_not_cre;

CREATE SEQUENCE nota_credito_det_id_det_not_credito_seq;

CREATE TABLE nota_credito_det (
                id_det_not_credito INTEGER NOT NULL DEFAULT nextval('nota_credito_det_id_det_not_credito_seq'),
                id_not_cre INTEGER NOT NULL,
                id_pro INTEGER NOT NULL,
                cantidad INTEGER NOT NULL,
                precio_unitario INTEGER NOT NULL,
                subtotal INTEGER NOT NULL,
                CONSTRAINT nota_credito_det_pk PRIMARY KEY (id_det_not_credito)
);


ALTER SEQUENCE nota_credito_det_id_det_not_credito_seq OWNED BY nota_credito_det.id_det_not_credito;

CREATE SEQUENCE det_recepcion_id_det_rec_seq;

CREATE TABLE det_recepcion (
                id_det_rec INTEGER NOT NULL DEFAULT nextval('det_recepcion_id_det_rec_seq'),
                id_rec INTEGER NOT NULL,
                id_pro INTEGER NOT NULL,
                cantidad_ordenada INTEGER NOT NULL,
                cantidad_recibida VARCHAR NOT NULL,
                precio_unitario INTEGER NOT NULL,
                subtotal INTEGER NOT NULL,
                estado VARCHAR NOT NULL,
                CONSTRAINT det_recepcion_pk PRIMARY KEY (id_det_rec)
);


ALTER SEQUENCE det_recepcion_id_det_rec_seq OWNED BY det_recepcion.id_det_rec;

CREATE SEQUENCE det_orden_comp_id_ord_comp_seq;

CREATE TABLE det_orden_comp (
                id_det_ord_comp INTEGER NOT NULL DEFAULT nextval('det_orden_comp_id_ord_comp_seq'),
                id_ord_comp INTEGER NOT NULL,
                 subtotal_det_ord_comp INTEGER NOT NULL,
                CONSTRAINT det_orden_comp_pk PRIMARY KEY (id_det_ord_comp)
);


ALTER SEQUENCE det_orden_comp_id_ord_comp_seq OWNED BY det_orden_comp.id_det_ord_comp;

CREATE SEQUENCE det_presupuesto_id_det_pres_seq;

CREATE TABLE det_presupuesto (
                id_det_pres INTEGER NOT NULL DEFAULT nextval('det_presupuesto_id_det_pres_seq'),
                id_pres INTEGER NOT NULL,
                cantidad INTEGER NOT NULL,
                precio_unitario INTEGER NOT NULL,
                CONSTRAINT det_presupuesto_pk PRIMARY KEY (id_det_pres)
);


ALTER SEQUENCE det_presupuesto_id_det_pres_seq OWNED BY det_presupuesto.id_det_pres;

CREATE SEQUENCE clientes_id_cli_seq;

CREATE TABLE clientes (
                id_cli INTEGER NOT NULL DEFAULT nextval('clientes_id_cli_seq'),
                nombre_cli VARCHAR(60) NOT NULL,
                apellido_cli VARCHAR(50) NOT NULL,
                edad_cli DATE NOT NULL,
                num_ci_ruc VARCHAR(30),
                telefono_cli VARCHAR(30) NOT NULL,
                id_ciu INTEGER NOT NULL,
                email_cli VARCHAR(150) NOT NULL,
                foto_cli VARCHAR(250),
                CONSTRAINT clientes_pk PRIMARY KEY (id_cli)
);


ALTER SEQUENCE clientes_id_cli_seq OWNED BY clientes.id_cli;

CREATE SEQUENCE carrito_id_carrito_seq;

CREATE TABLE carrito (
                id_carrito INTEGER NOT NULL DEFAULT nextval('carrito_id_carrito_seq'),
                id_cli INTEGER NOT NULL,
                fecha_carri DATE NOT NULL,
                hora_carri TIME NOT NULL,
                forma_pago_carri VARCHAR(30) NOT NULL,
                CONSTRAINT carrito_pk PRIMARY KEY (id_carrito)
);


ALTER SEQUENCE carrito_id_carrito_seq OWNED BY carrito.id_carrito;

CREATE SEQUENCE det_carrito_id_det_carrito_seq;

CREATE TABLE det_carrito (
                id_det_carrito INTEGER NOT NULL DEFAULT nextval('det_carrito_id_det_carrito_seq'),
                id_carrito INTEGER NOT NULL,
                id_depo INTEGER NOT NULL,
                id_pro INTEGER NOT NULL,
                cantidad INTEGER NOT NULL,
                precio_unitario INTEGER NOT NULL,
                subtotal INTEGER NOT NULL,
                CONSTRAINT det_carrito_pk PRIMARY KEY (id_det_carrito)
);


ALTER SEQUENCE det_carrito_id_det_carrito_seq OWNED BY det_carrito.id_det_carrito;

CREATE SEQUENCE ventas_id_ven_seq;

CREATE TABLE ventas (
                id_ven INTEGER NOT NULL DEFAULT nextval('ventas_id_ven_seq'),
                id_cli INTEGER NOT NULL,
                fecha_ven DATE NOT NULL,
                hora_ven TIME NOT NULL,
                forma_pago VARCHAR(30) NOT NULL,
                CONSTRAINT ventas_pk PRIMARY KEY (id_ven)
);


ALTER SEQUENCE ventas_id_ven_seq OWNED BY ventas.id_ven;

CREATE SEQUENCE det_ventas_id_det_ven_seq;

CREATE TABLE det_ventas (
                id_det_ven INTEGER NOT NULL DEFAULT nextval('det_ventas_id_det_ven_seq'),
                id_ven INTEGER NOT NULL,
                id_depo INTEGER NOT NULL,
                id_pro INTEGER NOT NULL,
                cantidad INTEGER NOT NULL,
                precio_unitario INTEGER NOT NULL,
                subtotal INTEGER NOT NULL,
                CONSTRAINT det_ventas_pk PRIMARY KEY (id_det_ven)
);


ALTER SEQUENCE det_ventas_id_det_ven_seq OWNED BY det_ventas.id_det_ven;

ALTER TABLE stock ADD CONSTRAINT depositos_stock_fk
FOREIGN KEY (id_depo)
REFERENCES depositos (id_depo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE pedido_compra ADD CONSTRAINT depositos_pedido_compra_fk
FOREIGN KEY (id_depo)
REFERENCES depositos (id_depo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_ventas ADD CONSTRAINT depositos_det_ventas_fk
FOREIGN KEY (id_depo)
REFERENCES depositos (id_depo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_carrito ADD CONSTRAINT depositos_det_carrito_fk
FOREIGN KEY (id_depo)
REFERENCES depositos (id_depo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE productos ADD CONSTRAINT categoria_productos_fk
FOREIGN KEY (id_cate)
REFERENCES categoria (id_cate)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE alta_producto_det ADD CONSTRAINT categoria_alta_producto_det_fk
FOREIGN KEY (id_cate)
REFERENCES categoria (id_cate)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_ventas ADD CONSTRAINT productos_det_ventas_fk
FOREIGN KEY (id_pro)
REFERENCES productos (id_pro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE stock ADD CONSTRAINT productos_stock_fk
FOREIGN KEY (id_pro)
REFERENCES productos (id_pro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_ped_compra ADD CONSTRAINT productos_det_ped_compra_fk
FOREIGN KEY (id_pro)
REFERENCES productos (id_pro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_recepcion ADD CONSTRAINT productos_det_recepcion_fk
FOREIGN KEY (id_pro)
REFERENCES productos (id_pro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE nota_credito_det ADD CONSTRAINT productos_nota_credito_det_fk
FOREIGN KEY (id_pro)
REFERENCES productos (id_pro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_carrito ADD CONSTRAINT productos_det_carrito_fk
FOREIGN KEY (id_pro)
REFERENCES productos (id_pro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ciudades ADD CONSTRAINT departamentos_ciudades_fk
FOREIGN KEY (id_dep)
REFERENCES departamentos (id_dep)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE clientes ADD CONSTRAINT ciudades_clientes_fk
FOREIGN KEY (id_ciu)
REFERENCES ciudades (id_ciu)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE proveedores ADD CONSTRAINT ciudades_proveedores_fk
FOREIGN KEY (id_ciu)
REFERENCES ciudades (id_ciu)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE usuarios ADD CONSTRAINT ciudades_usuarios_fk
FOREIGN KEY (id_ciu)
REFERENCES ciudades (id_ciu)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE pedido_compra ADD CONSTRAINT usuarios_pedido_compra_fk
FOREIGN KEY (id_usu)
REFERENCES usuarios (id_usu)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE recepcion ADD CONSTRAINT usuarios_recepcion_fk
FOREIGN KEY (id_usu)
REFERENCES usuarios (id_usu)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE alta_producto ADD CONSTRAINT usuarios_alta_producto_fk
FOREIGN KEY (id_usu)
REFERENCES usuarios (id_usu)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE alta_producto_det ADD CONSTRAINT alta_producto_alta_producto_det_fk
FOREIGN KEY (id_alta_pro)
REFERENCES alta_producto (id_alta_pro)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_ped_compra ADD CONSTRAINT pedido_compra_det_ped_compra_fk
FOREIGN KEY (id_ped_comp)
REFERENCES pedido_compra (id_ped_comp)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE presupuestos ADD CONSTRAINT pedido_compra_presupuestos_fk
FOREIGN KEY (id_ped_comp)
REFERENCES pedido_compra (id_ped_comp)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE presupuestos ADD CONSTRAINT proveedores_presupuestos_fk
FOREIGN KEY (id_prov)
REFERENCES proveedores (id_prov)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE nota_credito ADD CONSTRAINT proveedores_nota_credito_compra_fk
FOREIGN KEY (id_prov)
REFERENCES proveedores (id_prov)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_presupuesto ADD CONSTRAINT presupuestos_det_presupuesto_fk
FOREIGN KEY (id_pres)
REFERENCES presupuestos (id_pres)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE orden_compra ADD CONSTRAINT presupuestos_orden_compra_fk
FOREIGN KEY (id_pres)
REFERENCES presupuestos (id_pres)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_orden_comp ADD CONSTRAINT orden_compra_det_orden_comp_fk
FOREIGN KEY (id_ord_comp)
REFERENCES orden_compra (id_ord_comp)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE recepcion ADD CONSTRAINT orden_compra_recepcion_fk
FOREIGN KEY (id_ord_comp)
REFERENCES orden_compra (id_ord_comp)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_recepcion ADD CONSTRAINT recepcion_det_recepcion_fk
FOREIGN KEY (id_rec)
REFERENCES recepcion (id_rec)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE nota_credito ADD CONSTRAINT recepcion_nota_credito_compra_fk
FOREIGN KEY (id_rec)
REFERENCES recepcion (id_rec)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE nota_credito_det ADD CONSTRAINT nota_credito_nota_credito_det_fk
FOREIGN KEY (id_not_cre)
REFERENCES nota_credito (id_not_cre)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE ventas ADD CONSTRAINT clientes_ventas_fk
FOREIGN KEY (id_cli)
REFERENCES clientes (id_cli)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE carrito ADD CONSTRAINT clientes_carrito_fk
FOREIGN KEY (id_cli)
REFERENCES clientes (id_cli)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_carrito ADD CONSTRAINT carrito_det_carrito_fk
FOREIGN KEY (id_carrito)
REFERENCES carrito (id_carrito)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE det_ventas ADD CONSTRAINT ventas_det_ventas_fk
FOREIGN KEY (id_ven)
REFERENCES ventas (id_ven)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
