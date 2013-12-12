	@PersistenceContext
	transient EntityManager entityManager;

	public static final EntityManager entityManager() {
		EntityManager em = new ${name}().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static long count${name}() {
		return entityManager().createQuery("SELECT COUNT(o) FROM ${name} o", Long.class).getSingleResult();
	}

	public static List<${name}> findAll${name}s() {
		return entityManager().createQuery("SELECT o FROM ${name} o", ${name}.class).getResultList();
	}

	public static ${name} find${name}(Long id) {
		if (id == null) return null;
		return entityManager().find(${name}.class, id);
	}

	public static List<${name}> find${name}Entries(int firstResult, int maxResults) {
		return entityManager().createQuery("SELECT o FROM ${name} o", ${name}.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	@Transactional
	public void remove() {
		if (this.entityManager == null) this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			${name} attached = ${name}.find${name}(this.id);
			this.entityManager.remove(attached);
		}
	}

	@Transactional
	public void flush() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.flush();
	}

	@Transactional
	public void clear() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.clear();
	}

	@Transactional
	public ${name} merge() {
		if (this.entityManager == null) this.entityManager = entityManager();
		${name} merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
	}